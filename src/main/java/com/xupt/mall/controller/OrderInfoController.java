package com.xupt.mall.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.Cart;
import com.xupt.mall.entity.OrderInfo;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.service.OrderInfoService;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.util.OrderInfoSearch;
import com.xupt.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@CrossOrigin
@Controller
@RequestMapping("")
public class OrderInfoController {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("/order/manage/{page}/{limit}")
    @ResponseBody
    public Result getPageList(@PathVariable(name = "page") Long page,
                              @PathVariable(name = "limit") Long limit,
                              @RequestBody(required = false) OrderInfoSearch orderInfoSearch) {

        Page<OrderInfo> orderInfoPage = new Page<>(page, limit);
        orderInfoService.getPageList(orderInfoPage, orderInfoSearch);

        List<OrderInfo> orderInfoList = orderInfoPage.getRecords();
        long total = orderInfoPage.getTotal();

        for (OrderInfo orderInfo : orderInfoList) {
            Integer userId = orderInfo.getUserId();
            UserInfo userInfo = userInfoService.getUserInfoById(userId);
            if(userInfo == null){
                //orderInfoList.remove(orderInfo);
                continue;
            }
            orderInfo.setNickName(userInfo.getNickName());

        }

        return Result.ok().data("orderInfoList", orderInfoList).data("total", total);
    }

    //deleteOrderInfoById
    @DeleteMapping("/order/manage/{id}")
    @ResponseBody
    public Result deleteOrderInfoById(@PathVariable(name = "id") String id) {
        Boolean deleteFlag = orderInfoService.deleteOrderInfoById(id);
        if (deleteFlag) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("/order/manage/{id}")
    @ResponseBody
    public Result getOrderInfoById(@PathVariable(name = "id") String id) {

        OrderInfo orderInfo = orderInfoService.getOrderInfoById(id);
        return Result.ok().data("orderInfo", orderInfo);
    }

    @PutMapping("/order/manage/{id}")
    @ResponseBody
    public Result updateOrderInfo(@PathVariable(name = "id") String id,
                                  @RequestBody OrderInfo orderInfo) {
        Boolean updateFlag = orderInfoService.updateOrderInfo(orderInfo);
        if (updateFlag) {
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("/order/buy")
    @ResponseBody
    public Result buy(@RequestBody OrderInfo orderInfo, HttpSession session){

        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Integer userId ;
        if(userInfo != null) {
            userId = userInfo.getId();
        } else {
            return Result.error();
        }

        orderInfo.setUserId(userId);

        Boolean buyFlag = orderInfoService.buy(orderInfo,userInfo);
        if (buyFlag) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("/order/varifySkuCount/{skuId}/{skuCount}")
    @ResponseBody
    public Result varifySkuCount(@PathVariable(name = "skuId") Integer skuId,
                                 @PathVariable(name = "skuCount") Integer skuCount){

        Boolean falg = orderInfoService.varifySkuCount(skuId,skuCount);
        if (falg){
            return Result.ok();
        }
        return Result.error();
    }

}

