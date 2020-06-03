package com.xupt.mall.service;

import com.xupt.mall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
public interface CartService extends IService<Cart> {

    Boolean addToCart(Integer skuId,Integer userId);

    List<Cart> listItem(Integer userId);

    Cart billCart(Integer cartId);


    Cart updateItem(Integer userId, Integer cartId, Integer skuId, Integer skuCountId);

    /**
     * 根据购物项ID删除购物项
     * @param cartId
     * @return: java.lang.Boolean
     */
    Boolean deleteItem(Integer cartId);
}
