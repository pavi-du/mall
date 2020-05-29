package com.xupt.mall.service;

import com.xupt.mall.entity.BaseAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface BaseAttrService extends IService<BaseAttr> {

    /**
     * 获取基本属性和基本属性值
     * @param
     * @return: java.util.List<com.xupt.mall.entity.BaseAttr>
     */
    List<BaseAttr> listBaseAttr();

}
