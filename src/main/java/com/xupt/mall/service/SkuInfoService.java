package com.xupt.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.mall.util.SkuInfoSearch;
import com.xupt.mall.util.UserInfoSearch;
import com.xupt.mall.vo.UploadSkuInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface SkuInfoService extends IService<SkuInfo> {

    /**
     * 初始化商家商品的信息
     * @param
     * @return: com.xupt.mall.vo.UploadSkuInfo
     */
    UploadSkuInfo initUploadSkuInfo();

    Boolean saveUploadSkuInfo(UploadSkuInfo uploadSkuInfo);

    void pageSearch(Page<SkuInfo> skuInfoPage, SkuInfoSearch skuInfoSearch);

    SkuInfo getSkuInfoById(Integer id);

    Boolean updateSkuInfo(SkuInfo skuInfo);

    Boolean deleteSkuInfoById(Integer id);

    List<SkuInfo> getSkuInfoByCatalogId(Integer catalogId);
}
