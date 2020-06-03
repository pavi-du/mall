package com.xupt.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.mapper.UserInfoMapper;
import com.xupt.mall.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.util.CookieUtil;
import com.xupt.mall.util.UserInfoSearch;
import com.xupt.mall.util.WebConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {


    @Override
    public void pageSearch(Page<UserInfo> userInfoPage, UserInfoSearch userInfoSearch) {
        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();

        if(userInfoSearch == null){
            baseMapper.selectPage(userInfoPage,null);
            return;
        }

        if (userInfoSearch != null) {
            if (!StringUtils.isEmpty(userInfoSearch.getLoginName())) {
                userInfoQueryWrapper.like("login_name",userInfoSearch.getLoginName());
            }
        }

        baseMapper.selectPage(userInfoPage,userInfoQueryWrapper);
    }

    @Override
    public UserInfo getUserInfoById(Integer id) {

        UserInfo user = new UserInfo();
        user.setId(id);
        UserInfo userInfo = baseMapper.selectById(user);
        return userInfo;
    }

    @Override
    public boolean deleteUserInfoById(Integer id) {
        UserInfo user = new UserInfo();
        user.setId(id);
        int count = baseMapper.deleteById(user);
        if(count == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUserInfo(UserInfo userInfo) {


        int count = baseMapper.updateById(userInfo);

        if(count == 1){
            return true;
        }
        return false;
    }

    @Override
    public UserInfo frontLogin(UserInfo userInfo) {

        if (userInfo == null) {
            return userInfo;
        }

        String passwd = DigestUtils.md5DigestAsHex(userInfo.getPasswd().getBytes());
        userInfo.setPasswd(passwd);

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();

        userInfoQueryWrapper.eq("LOGIN_NAME",userInfo.getLoginName());
        userInfoQueryWrapper.eq("PASSWD",userInfo.getPasswd());

        List<UserInfo> userInfoList = baseMapper.selectList(userInfoQueryWrapper);

        if(userInfoList!= null &&userInfoList.size() == 1){
            return userInfoList.get(0);
        }

        return null;
    }

    @Override
    public Boolean frontRegist(UserInfo userInfo) {

        if(userInfo == null) {
            return false;
        }

        String passwd = DigestUtils.md5DigestAsHex(userInfo.getPasswd().getBytes());
        userInfo.setPasswd(passwd);

        QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
        userInfoQueryWrapper.eq("LOGIN_NAME",userInfo.getLoginName());

        UserInfo user = baseMapper.selectOne(userInfoQueryWrapper);
        if(user != null) {
            return false;
        }
        baseMapper.insert(userInfo);

        return true;
    }

    @Override
    public void setCookie(HttpServletRequest request, HttpServletResponse response, UserInfo userInfoRes) {


        String value = CookieUtil.getCookieValue(request, "user", false);
        if(StringUtils.isEmpty(value)){
            CookieUtil.setCookie(request,response,"user",
                    userInfoRes.getNickName(), WebConst.COOKIE_MAXAGE,false);

        }


    }

    @Override
    public Boolean deleteCookie(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        String value = CookieUtil.getCookieValue(request, "user", false);
        if(!StringUtils.isEmpty(value)){
            CookieUtil.deleteCookie(request,response,"user");
        }
        session.removeAttribute("userInfo");

        return true;
    }

    @Override
    public boolean updateUserPasswd(UserInfo userInfo) {


        UserInfo user = getUserInfoById(userInfo.getId());
        String passwd = userInfo.getPasswd();
        String mPasswd = DigestUtils.md5DigestAsHex(passwd.getBytes());
        user.setPasswd(mPasswd);
        boolean updateUserInfo = updateUserInfo(user);
        return updateUserInfo;
    }
}
