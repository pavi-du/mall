package com.xupt.mall.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.Cart;
import com.xupt.mall.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.util.OrderInfoSearch;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface OrderInfoService extends IService<OrderInfo> {

    void getPageList(Page<OrderInfo> orderInfoPage, OrderInfoSearch orderInfoSearch);

    Boolean deleteOrderInfoById(String id);

    OrderInfo getOrderInfoById(String id);

    Boolean updateOrderInfo(OrderInfo orderInfo);

    /**
     * 下单
     * @param orderInfo
     * @param userInfo
     * @return: java.lang.Boolean
     */
    Boolean buy(OrderInfo orderInfo, UserInfo userInfo);

    Boolean varifySkuCount(Integer skuId, Integer skuCount);
}
