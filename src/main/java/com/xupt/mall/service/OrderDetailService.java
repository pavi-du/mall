package com.xupt.mall.service;

import com.xupt.mall.entity.OrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface OrderDetailService extends IService<OrderDetail> {

    Boolean saveOrderDetail(OrderDetail orderDetail);
}
