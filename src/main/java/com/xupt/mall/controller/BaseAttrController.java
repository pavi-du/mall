package com.xupt.mall.controller;


import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.service.BaseAttrService;
import com.xupt.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/attr")
public class BaseAttrController {


    @Autowired
    private BaseAttrService baseAttrService;

    @GetMapping("manage/list")
    public Result listBaseAttr(){

        List<BaseAttr> baseAttrList = baseAttrService.listBaseAttr();

        return Result.ok().data("baseAttrList",baseAttrList);
    }

}

