package com.xupt.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.Catalog;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.mapper.CatalogMapper;
import com.xupt.mall.service.CatalogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.util.CatalogSearch;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Service
public class CatalogServiceImpl extends ServiceImpl<CatalogMapper, Catalog> implements CatalogService {

    @Override
    public void pageSearch(Page<Catalog> catalogPage, CatalogSearch catalogSearch) {
        QueryWrapper<Catalog> catalogQueryWrapper = new QueryWrapper<>();

        if(catalogSearch == null){
            baseMapper.selectPage(catalogPage,null);
            return;
        }

        if (catalogSearch != null) {
            if (!StringUtils.isEmpty(catalogSearch.getCatalogName())) {
                catalogQueryWrapper.like("CATALOG_NAME",catalogSearch.getCatalogName());
            }
        }

        baseMapper.selectPage(catalogPage,catalogQueryWrapper);
    }

    @Override
    public Catalog getCatalogById(String id) {
        Catalog catalogTemp = new Catalog();
        catalogTemp.setId(Integer.parseInt(id));
        Catalog catalog = baseMapper.selectById(catalogTemp);
        return catalog;
    }

    @Override
    public boolean deleteCatalogById(String id) {
        Catalog catalog = new Catalog();
        catalog.setId(Integer.parseInt(id));
        int count = baseMapper.deleteById(catalog);
        if(count == 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCatalog(Catalog catalog) {
        int count = baseMapper.updateById(catalog);

        if(count == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<Catalog> listCatalog() {
        return baseMapper.selectList(null);
    }

    @Override
    public Boolean saveCatalog(Catalog catalog) {
        int insert = baseMapper.insert(catalog);
        if(insert == 1){
            return true;
        }
        return false;

    }
}
