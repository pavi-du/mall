package com.xupt.mall.controller;


import com.xupt.mall.service.OrderDetailService;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.util.OrderInfoSearch;
import com.xupt.mall.util.Result;
import com.xupt.mall.vo.OrderResVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Controller
@RequestMapping("/order/detail")
public class OrderDetailController {


    @Autowired
    private OrderDetailService  orderDetailService;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("list")
    @ResponseBody
    public Result list(){
        return Result.ok().data("orderDetail",orderDetailService.list(null));
    }

    //getPageList

    @GetMapping("toOrderDetail")
    public String toOrderDetail(HttpSession session,ModelMap map){

        Integer userId = userInfoService.getUserId(session);
        if(userId != null){
            List<OrderResVo> orderResVoList = orderDetailService.listOrderDetail(userId);

            if(orderResVoList != null && orderResVoList.size() > 0){
                map.put("orderResVoList",orderResVoList);
            }
        }

        return "orderDetail";
    }


}

