package com.xupt.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.Catalog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.mall.util.CatalogSearch;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface CatalogService extends IService<Catalog> {

    void pageSearch(Page<Catalog> catalogPage, CatalogSearch catalogSearch);

    Catalog getCatalogById(String id);

    boolean deleteCatalogById(String id);

    boolean updateCatalog(Catalog catalog);

    List<Catalog> listCatalog();

    Boolean saveCatalog(Catalog catalog);
}
