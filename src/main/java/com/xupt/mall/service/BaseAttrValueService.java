package com.xupt.mall.service;

import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.BaseAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.mall.vo.BaseAttrValueVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface BaseAttrValueService extends IService<BaseAttrValue> {

    List<BaseAttrValue> listBaseAttrValue();

    /**
     * 添加基本属性值
     * @param baseAttrValueVO
     * @return: java.lang.Boolean
     */
    Boolean addBaseAttrValue(BaseAttrValueVO baseAttrValueVO);

    Boolean deleteBaseAttrValue(String id);

}
