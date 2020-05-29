package com.xupt.mall.service;

import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.SkuSaleAttrValue;
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
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValue> {

    Boolean saveSaleAttrValueList(List<String> baseAttrIdList, List<BaseAttr> baseAttrList, Integer skuInfoId);
}
