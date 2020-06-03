package com.xupt.mall.controller;


import com.xupt.mall.entity.Cart;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.service.CartService;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.util.Result;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@Controller
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private CartService cartService;
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("addToCart/{skuId}")
    @ResponseBody
    public Result addToCart(@PathVariable(name = "skuId") Integer skuId, HttpSession session) {

        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Integer userId;
        if (userInfo != null) {
            userId = userInfo.getId();
        } else {
            return Result.error();
        }

        Boolean addToCartFlag = cartService.addToCart(skuId, userId);
        if (addToCartFlag) {
            return Result.ok();
        }
        return Result.error();
    }

    @GetMapping("toCart")
    public String toCart() {

        return "cart";
    }

    @GetMapping("listItem")
    @ResponseBody
    public Result listItem(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Integer userId;
        if (userInfo != null) {
            userId = userInfo.getId();
        } else {
            return Result.error();
        }

        List<Cart> cartList = cartService.listItem(userId);
        if (cartList != null && cartList.size() > 0) {
            return Result.ok().data("cartList", cartList);
        }
        return Result.error();
    }

    @GetMapping("toBill/{cartId}")
    public String toBill(@PathVariable(name = "cartId") Integer cartId,
                         ModelMap map) {

        Cart cart = cartService.billCart(cartId);
        map.put("cart", cart);

        return "bill";
    }

    @PutMapping("updateItem/{cartId}/{skuId}/{skuCountId}")
    @ResponseBody
    public Result updateItem(@PathVariable(name = "cartId") Integer cartId,
                             @PathVariable(name = "skuId") Integer skuId,
                             @PathVariable(name = "skuCountId") Integer skuCountId,
                             HttpSession session) {

        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        Integer userId;
        if (userInfo != null) {
            userId = userInfo.getId();
        } else {
            return Result.error();
        }

        Cart cartItem = cartService.updateItem(userId,cartId,skuId,skuCountId);

        if (cartItem != null) {
            return Result.ok().data("cartItem", cartItem);
        }
        return Result.error();
    }

    @DeleteMapping("/deleteItem/{cartId}")
    @ResponseBody
    public Result deleteItem(@PathVariable(name = "cartId")Integer cartId){

        Boolean deleteItemFlag = cartService.deleteItem(cartId);
        if (deleteItemFlag) {
            return Result.ok();
        }
        return Result.error();
    }



}

