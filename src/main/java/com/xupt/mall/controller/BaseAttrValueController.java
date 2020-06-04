package com.xupt.mall.controller;


import com.xupt.mall.service.BaseAttrValueService;
import com.xupt.mall.util.Result;
import com.xupt.mall.vo.BaseAttrValueVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/attr/value")
public class BaseAttrValueController {


    @Autowired
    private BaseAttrValueService baseAttrValueService;


    @PostMapping("manage/add")
    public Result addBaseAttrValue(@RequestBody BaseAttrValueVO baseAttrValueVO){

        Boolean addFlag = baseAttrValueService.addBaseAttrValue(baseAttrValueVO);

        if(addFlag){
            return Result.ok();
        }
        return Result.error();
    }

    @DeleteMapping("manage/{id}")
    public Result deleteBaseAttrValue(@PathVariable(name = "id")Integer id){

        Boolean deleteFlag = baseAttrValueService.deleteBaseAttrValue(id);
        if(deleteFlag){
            return Result.ok();
        }

        return Result.error();
    }

}

