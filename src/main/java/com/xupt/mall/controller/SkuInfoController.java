package com.xupt.mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.Catalog;
import com.xupt.mall.entity.SkuInfo;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.service.CatalogService;
import com.xupt.mall.service.SkuInfoService;
import com.xupt.mall.util.Result;
import com.xupt.mall.util.SkuInfoSearch;
import com.xupt.mall.util.UserInfoSearch;
import com.xupt.mall.vo.UploadSkuInfo;
import io.swagger.models.auth.In;
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
@RequestMapping("/skuInfo/")
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private CatalogService catalogServicel;

    @GetMapping("manage/init")
    public Result initUploadSkuInfo(){

        UploadSkuInfo uploadSkuInfo = skuInfoService.initUploadSkuInfo();

        return Result.ok().data("uploadSkuInfo",uploadSkuInfo);
    }


    //saveUploadSkuInfo(uploadSkuInfo)
    @PostMapping("manage/save")
    public Result saveUploadSkuInfo(@RequestBody UploadSkuInfo uploadSkuInfo){
        Boolean saveFlag = skuInfoService.saveUploadSkuInfo(uploadSkuInfo);

        if(saveFlag) {
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("manage/{page}/{limit}")
    public Result getPageList(@PathVariable(name = "page") Long page,
                              @PathVariable(name = "limit") Long limit,
                              @RequestBody SkuInfoSearch skuInfoSearch){

        Page<SkuInfo> skuInfoPage = new Page<>(page,limit);
        skuInfoService.pageSearch(skuInfoPage,skuInfoSearch);
        List<SkuInfo> skuInfoList = skuInfoPage.getRecords();

        for (SkuInfo skuInfo : skuInfoList) {
            Catalog catalog = catalogServicel.getCatalogById(skuInfo.getCatalogId());
            skuInfo.setCatalogName(catalog.getCatalogName());
        }

        return Result.ok().data("skuInfoList",skuInfoList).data("total",skuInfoPage.getTotal());
    }

    @GetMapping("manage/{id}")
    public Result getSkuInfoById(@PathVariable String id){

        SkuInfo skuInfo = skuInfoService.getSkuInfoById(id);
        return Result.ok().data("skuInfo",skuInfo);
    }

    @PutMapping("manage/{id}")
    public Result updateSkuInfo(@PathVariable String id,
                                @RequestBody SkuInfo skuInfo){

        //skuInfo.setId(Integer.parseInt(id));
        Boolean updateSkuInfo = skuInfoService.updateSkuInfo(skuInfo);
        if(updateSkuInfo){
            return Result.ok();
        }
        return Result.error();
    }

    @DeleteMapping("manage/{id}")
    public Result deleteSkuInfoById(@PathVariable String id){

        //skuInfo.setId(Integer.parseInt(id));
        Boolean delete = skuInfoService.deleteSkuInfoById(id);
        if(delete){
            return Result.ok();
        }
        return Result.error();
    }

}

