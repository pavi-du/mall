package com.xupt.mall.controller;


import com.xupt.mall.service.OrderDetailService;
import com.xupt.mall.util.OrderInfoSearch;
import com.xupt.mall.util.Result;
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
@RestController
@RequestMapping("/mall/order/detail")
public class OrderDetailController {


    @Autowired
    private OrderDetailService  orderDetailService;

    @GetMapping("list")
    public Result list(){
        return Result.ok().data("orderDetail",orderDetailService.list(null));
    }

    //getPageList


}

