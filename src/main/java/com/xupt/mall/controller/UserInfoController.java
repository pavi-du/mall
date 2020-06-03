package com.xupt.mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.util.CookieUtil;
import com.xupt.mall.util.Result;
import com.xupt.mall.util.UserInfoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@CrossOrigin
@Controller
@RequestMapping("")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/user/login")
    @ResponseBody
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @GetMapping("/user/info")
    @ResponseBody
    public Result info() {
        //"data":{"roles":["admin"],"name":"admin",
        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
        return Result.ok().data("roles", "[\"admin\"]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("/user/manage/{page}/{limit}")
    @ResponseBody
    public Result getPageList(@PathVariable(name = "page") Long page,
                              @PathVariable(name = "limit") Long limit,
                              @RequestBody UserInfoSearch userInfoSearch) {

        Page<UserInfo> userInfoPage = new Page<>(page, limit);
        userInfoService.pageSearch(userInfoPage, userInfoSearch);
        List<UserInfo> userInfoList = userInfoPage.getRecords();
        return Result.ok().data("userInfoList", userInfoList).data("total", userInfoPage.getTotal());
    }


    @GetMapping("/user/manage/{id}")
    @ResponseBody
    public Result getUserInfoById(@PathVariable(name = "id") Integer id) {

        UserInfo userInfo = userInfoService.getUserInfoById(id);
        return Result.ok().data("userInfo", userInfo);
    }

    @DeleteMapping("/user/manage/{id}")
    @ResponseBody
    public Result deleteUserInfoById(@PathVariable(name = "id") Integer id) {

        boolean flag = userInfoService.deleteUserInfoById(id);

        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }


    @PutMapping("/user/manage/{id}")
    @ResponseBody
    public Result updateUserInfo(@PathVariable(name = "id") String id,
                                 @RequestBody UserInfo userInfo) {


        userInfo.setId(Integer.parseInt(id));
        boolean flag = userInfoService.updateUserInfo(userInfo);

        if (flag) {
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("/user/front/login")
    @ResponseBody
    public Result frontLogin(@RequestBody UserInfo userInfo, HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        if (userInfo == null) {
            return Result.error();
        } else {
            System.out.println(userInfo);
            UserInfo userInfoRes = userInfoService.frontLogin(userInfo);
            if (userInfoRes == null) {
                return Result.error();
            }

            userInfoService.setCookie(request, response, userInfoRes);
            userInfoRes.setPasswd("");
            session.setAttribute("userInfo",userInfoRes);
            return Result.ok();
        }

    }

    @PostMapping("/user/front/regist")
    @ResponseBody
    public Result frontRegist(@RequestBody UserInfo userInfo) {

        Boolean registFlag = userInfoService.frontRegist(userInfo);

        if (registFlag) {
            return Result.ok().message("注册成功");
        }
        return Result.error().message("用户已经存在");

    }

    @GetMapping("/user/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/index")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/user/toRegist")
    public String toRegist(){

        return "regist";
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response,HttpSession session){

       //CookieUtil.deleteCookie(request,response,"user");
        Boolean logoutFlag = userInfoService.deleteCookie(request,response,session);
        return "index";
    }


    @GetMapping("/user/toUpdatePasswd")
    public String toUpdatePasswd(ModelMap map,HttpSession session){

        Integer userId = getUserId(session);
        if(userId != -1){
            UserInfo userInfo = userInfoService.getUserInfoById(userId);
            map.put("userInfo",userInfo);
            return "updatePasswd";
        }

        return "index";
    }

    private Integer getUserId(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Integer userId ;
        if(userInfo != null) {
            userId = userInfo.getId();
            return userId;
        }
        return -1;
    }

    @PutMapping("/user/updatePasswd")
    @ResponseBody
    public Result updatePasswd(@RequestBody UserInfo userInfo,HttpSession session ){

        Integer userId = getUserId(session);
        userInfo.setId(userId);
        if(userId != -1){
            userInfo.setId(userId);
            boolean updateUserInfo = userInfoService.updateUserPasswd(userInfo);

            if (updateUserInfo) {
                return Result.ok().message("更新成功");
            }
        }

        return Result.error().message("更新失败");
    }

}

