package com.xupt.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.Catalog;
import com.xupt.mall.entity.SkuInfo;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.mapper.SkuInfoMapper;
import com.xupt.mall.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.util.SkuInfoSearch;
import com.xupt.mall.util.UserInfoSearch;
import com.xupt.mall.vo.UploadSkuInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@CrossOrigin
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {


    @Autowired
    private CatalogService catalogService;
    @Autowired
    private BaseAttrService baseAttrService;
    @Autowired
    private SkuAttrValueService skuAttrValueService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public UploadSkuInfo initUploadSkuInfo() {

        List<Catalog> catalogList = catalogService.listCatalog();
        List<BaseAttr> baseAttrList = baseAttrService.listBaseAttr();

        UploadSkuInfo uploadSkuInfo = new UploadSkuInfo();
        uploadSkuInfo.setCatalogLit(catalogList);
        uploadSkuInfo.setBaseAttrList(baseAttrList);
        uploadSkuInfo.setSkuCount(String.valueOf(100));
        return uploadSkuInfo;
    }

    @Override
    public Boolean saveUploadSkuInfo(UploadSkuInfo uploadSkuInfo) {

        List<Integer> catalogIdList = uploadSkuInfo.getCatalogIdList();
        for (Integer catalogId : catalogIdList) {
            SkuInfo skuInfo = new SkuInfo();
            skuInfo.setCatalogId(catalogId);
            BeanUtils.copyProperties(uploadSkuInfo, skuInfo);
            baseMapper.insert(skuInfo);

            Integer skuInfoId = skuInfo.getId();
            System.out.println(skuInfoId);

            List<BaseAttr> baseAttrList = uploadSkuInfo.getBaseAttrList();

            List<Integer> baseAttrIdList = uploadSkuInfo.getBaseAttrIdList();
            Boolean saleAttr = skuAttrValueService.saveAttrValueList(baseAttrIdList, baseAttrList, skuInfoId);
            Boolean saleAttrValue = skuSaleAttrValueService.saveSaleAttrValueList(baseAttrIdList, baseAttrList, skuInfoId);
            if (!saleAttr || !saleAttrValue) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void pageSearch(Page<SkuInfo> skuInfoPage, SkuInfoSearch skuInfoSearch) {
        QueryWrapper<SkuInfo> skuInfoQueryWrapper = new QueryWrapper<>();
        if (skuInfoSearch == null) {
            baseMapper.selectPage(skuInfoPage, null);
            return;
        }

        if (skuInfoSearch != null) {
            if (!StringUtils.isEmpty(skuInfoSearch.getSkuName())) {
                skuInfoQueryWrapper.like("sku_name", skuInfoSearch.getSkuName());
            }
        }

        baseMapper.selectPage(skuInfoPage, skuInfoQueryWrapper);
        return;
    }

    @Override
    public SkuInfo getSkuInfoById(Integer id) {

        SkuInfo skuInfo = baseMapper.selectById(id);

        return skuInfo;
    }

    @Override
    public Boolean updateSkuInfo(SkuInfo skuInfo) {
        int update = baseMapper.updateById(skuInfo);
        if (update == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteSkuInfoById(Integer id) {
        int delete = baseMapper.deleteById(id);
        if(delete == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<SkuInfo> getSkuInfoByCatalogId(Integer catalogId) {

        QueryWrapper<SkuInfo> skuInfoQueryWrapper = new QueryWrapper<>();
        skuInfoQueryWrapper.eq("CATALOG_ID", catalogId);

        List<SkuInfo> skuInfoList = baseMapper.selectList(skuInfoQueryWrapper);

        return skuInfoList;
    }
}
