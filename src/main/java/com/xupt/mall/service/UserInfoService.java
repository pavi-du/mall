package com.xupt.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.mall.util.UserInfoSearch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface UserInfoService extends IService<UserInfo> {

    void pageSearch(Page<UserInfo> userInfoPage, UserInfoSearch o);

    UserInfo getUserInfoById(Integer id);

    boolean deleteUserInfoById(Integer id);

    boolean updateUserInfo(UserInfo id);

    UserInfo frontLogin(UserInfo userInfo);

    Boolean frontRegist(UserInfo userInfo);

    /**
     * 设置cookie
     * @param request
     * @param response
     * @param userInfoRes
     * @return: void
     */
    void setCookie(HttpServletRequest request, HttpServletResponse response, UserInfo userInfoRes);

    Boolean deleteCookie(HttpServletRequest request, HttpServletResponse response, HttpSession session);

    boolean updateUserPasswd(UserInfo userInfo);

    Integer getUserId(HttpSession session);
}
