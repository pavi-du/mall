package com.xupt.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.mall.entity.Cart;
import com.xupt.mall.entity.SkuInfo;
import com.xupt.mall.mapper.CartMapper;
import com.xupt.mall.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.service.SkuInfoService;
import com.xupt.mall.util.SkuInfoSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {


    @Autowired
    private SkuInfoService skuInfoService;


    @Override
    public Boolean addToCart(Integer skuId, Integer userId) {

        SkuInfo skuInfo = skuInfoService.getSkuInfoById(skuId);
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setSkuId(skuInfo.getId());

        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("USER_ID", cart.getUserId());
        cartQueryWrapper.eq("SKU_ID", cart.getSkuId());

        Cart cartRes = baseMapper.selectOne(cartQueryWrapper);

        Integer skuCount = skuInfo.getSkuCount();

        if (cartRes != null) {
            cartRes.setSkuCount(cartRes.getSkuCount()+1);
            cartRes.setCartPrice(cartRes.getCartPrice() + skuInfo.getSkuPrice());
            baseMapper.updateById(cartRes);

            return true;
        }

        cart.setSkuCount(1);
        cart.setCartPrice(skuInfo.getSkuPrice());
        cart.setSkuName(skuInfo.getSkuName());
        int insert = baseMapper.insert(cart);
        if(insert == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<Cart> listItem(Integer userId) {

        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("USER_ID",userId);

        List<Cart> cartList = baseMapper.selectList(cartQueryWrapper);

        return cartList;
    }

    @Override
    public Cart billCart(Integer cartId) {

        Cart cart = baseMapper.selectById(cartId);

        return cart;
    }

    @Override
    public Cart updateItem(Integer userId, Integer cartId, Integer skuId, Integer skuCountId) {


        Cart cart = new Cart();
        cart.setId(cartId);
        cart.setSkuCount(skuCountId);
        SkuInfo skuInfo = skuInfoService.getSkuInfoById(skuId);
        cart.setCartPrice(skuCountId*skuInfo.getSkuPrice());

        baseMapper.updateById(cart);

        Cart cartRes = baseMapper.selectById(cartId);
        return cartRes;
    }

    @Override
    public Boolean deleteItem(Integer cartId) {

        int deleteItem = baseMapper.deleteById(cartId);

        return deleteItem == 1;
    }


}
