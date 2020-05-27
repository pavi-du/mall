package com.xupt.mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.util.Result;
import com.xupt.mall.util.UserInfoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("login")
    //@ResponseBody
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    @GetMapping("info")
    //@ResponseBody
    public Result info() {
        //"data":{"roles":["admin"],"name":"admin",
        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
        return Result.ok().data("roles", "[\"admin\"]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("manage/{page}/{limit}")
    public Result getPageList(@PathVariable(name = "page") Long page,
                              @PathVariable(name = "limit") Long limit,
                              @RequestBody UserInfoSearch userInfoSearch){

        Page<UserInfo> userInfoPage = new Page<>(page,limit);
        userInfoService.pageSearch(userInfoPage,userInfoSearch);
        List<UserInfo> userInfoList = userInfoPage.getRecords();
        return Result.ok().data("userInfoList",userInfoList).data("total",userInfoPage.getTotal());
    }


    @GetMapping("manage/{id}")
    public Result getUserInfoById(@PathVariable(name = "id")String id){

        UserInfo userInfo = userInfoService.getUserInfoById(id);
        return Result.ok().data("userInfo",userInfo);
    }

    @DeleteMapping("manage/{id}")
    public Result deleteUserInfoById(@PathVariable(name = "id")String id){

        boolean flag = userInfoService.deleteUserInfoById(id);

        if(flag){
            return Result.ok();
        }
        return Result.error();
    }


    @PutMapping("manage/{id}")
    public Result updateUserInfo(@PathVariable(name = "id") String id,
                                 @RequestBody UserInfo userInfo){


        userInfo.setId(Integer.parseInt(id));
        boolean flag = userInfoService.updateUserInfo(userInfo);

        if(flag){
            return Result.ok();
        }
        return Result.error();
    }

}

