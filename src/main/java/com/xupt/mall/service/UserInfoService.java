package com.xupt.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.mall.util.UserInfoSearch;

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

    UserInfo getUserInfoById(String id);

    boolean deleteUserInfoById(String id);

    boolean updateUserInfo(UserInfo id);
}
