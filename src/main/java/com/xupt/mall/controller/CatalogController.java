package com.xupt.mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.Catalog;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.service.CatalogService;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.util.CatalogSearch;
import com.xupt.mall.util.Result;
import com.xupt.mall.util.UserInfoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@CrossOrigin
@RestController
@RequestMapping("/catalog")
public class CatalogController {


    @Autowired
    private CatalogService catalogService;

    @PostMapping("manage/{page}/{limit}")
    public Result getPageList(@PathVariable(name = "page") Long page,
                              @PathVariable(name = "limit") Long limit,
                              @RequestBody CatalogSearch catalogSearch){

        Page<Catalog> catalogPage = new Page<>(page,limit);
        catalogService.pageSearch(catalogPage,catalogSearch);
        List<Catalog> catalogList = catalogPage.getRecords();
        return Result.ok().data("catalogList",catalogList).data("total",catalogPage.getTotal());
    }


    @GetMapping("manage/{id}")
    public Result getCatalogById(@PathVariable(name = "id")String id){

        Catalog catalog = catalogService.getCatalogById(id);
        return Result.ok().data("catalog",catalog);
    }

    @DeleteMapping("manage/{id}")
    public Result deleteCatalogById(@PathVariable(name = "id")String id){

        boolean flag = catalogService.deleteCatalogById(id);

        if(flag){
            return Result.ok();
        }
        return Result.error();
    }


    @PutMapping("manage/{id}")
    public Result updateCatalog(@PathVariable(name = "id") String id,
                                 @RequestBody Catalog catalog){


        catalog.setId(Integer.parseInt(id));
        boolean flag = catalogService.updateCatalog(catalog);

        if(flag){
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("manage/save")
    public Result saveCatalog(@RequestBody Catalog catalog){

        Boolean saveCatalog = catalogService.saveCatalog(catalog);
        if(saveCatalog){
            return Result.ok();
        }
        return Result.error();
    }


}

