package com.xupt.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.mapper.UserInfoMapper;
import com.xupt.mall.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.util.UserInfoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public UserInfo getUserInfoById(String id) {

        UserInfo user = new UserInfo();
        user.setId(Integer.parseInt(id));
        UserInfo userInfo = baseMapper.selectById(user);
        return userInfo;
    }

    @Override
    public boolean deleteUserInfoById(String id) {
        UserInfo user = new UserInfo();
        user.setId(Integer.parseInt(id));
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
}
