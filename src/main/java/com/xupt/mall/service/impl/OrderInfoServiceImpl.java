package com.xupt.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xupt.mall.entity.*;
import com.xupt.mall.mapper.OrderInfoMapper;
import com.xupt.mall.service.OrderDetailService;
import com.xupt.mall.service.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.service.SkuInfoService;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.util.OrderInfoSearch;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {


    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private SkuInfoService skuInfoService;

    @Override
    public void getPageList(Page<OrderInfo> orderInfoPage, OrderInfoSearch orderInfoSearch) {

        if (orderInfoSearch == null) {
            baseMapper.selectPage(orderInfoPage, null);
            return;
        }
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>();
        Date createTime = orderInfoSearch.getCreateTime();
        Date expireTime = orderInfoSearch.getExpireTime();

        if (createTime != null) {
            orderInfoQueryWrapper.ge("CREATE_TIME", createTime);
        }

        if (expireTime != null) {
            orderInfoQueryWrapper.le("EXPIRE_TIME", expireTime);
        }

        baseMapper.selectPage(orderInfoPage, orderInfoQueryWrapper);
        return;

    }

    @Override
    public Boolean deleteOrderInfoById(String id) {
        int delete = baseMapper.deleteById(Integer.parseInt(id));
        if (delete == 1) {
            return true;
        }
        return false;
    }

    @Override
    public OrderInfo getOrderInfoById(String id) {

        OrderInfo orderInfo = baseMapper.selectById(Integer.parseInt(id));
        UserInfo userInfo = userInfoService.getUserInfoById(orderInfo.getUserId());
        orderInfo.setNickName(userInfo.getNickName());

        return orderInfo;
    }

    @Override
    public Boolean updateOrderInfo(OrderInfo orderInfo) {

        int update = baseMapper.updateById(orderInfo);
        if (update == 1) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Boolean buy(OrderInfo orderInfo, UserInfo userInfo) {


        if (orderInfo == null) {
            return false;
        }

        Integer skuId = orderInfo.getSkuId();
        Integer skuCount = orderInfo.getSkuCount();
        SkuInfo skuInfo = new SkuInfo();
        skuInfo.setId(skuId);

        SkuInfo skuInfoOld = skuInfoService.getSkuInfoById(skuId);
        skuInfoOld.setSkuCount(skuInfoOld.getSkuCount() - skuCount);
        skuInfoService.updateSkuInfo(skuInfoOld);

        LocalDate localDate = LocalDate.now();
        LocalDate expireDate = localDate.plusDays(3L);
        orderInfo.setCreateTime(localDate);
        orderInfo.setExpireTime(expireDate);
        orderInfo.setOrderStatus("close");
        int insert = baseMapper.insert(orderInfo);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(orderInfo.getId());
        orderDetail.setSkuId(orderInfo.getSkuId());
        orderDetail.setSkuName(orderInfo.getSkuName());
        orderDetail.setSkuNum(orderInfo.getSkuCount());


        Boolean saveOrderDetailFlag = orderDetailService.saveOrderDetail(orderDetail);

        if (insert == 1 && saveOrderDetailFlag == true) {
            return true;
        }

        return true;
    }

    @Override
    public Boolean varifySkuCount(Integer skuId, Integer skuCount) {

        SkuInfo skuInfo = skuInfoService.getSkuInfoById(skuId);

        if (skuInfo != null && (skuInfo.getSkuCount() >= skuCount)) {
            return true;
        }

        return false;
    }
}
