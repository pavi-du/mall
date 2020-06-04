package com.xupt.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.mall.entity.OrderDetail;
import com.xupt.mall.entity.OrderInfo;
import com.xupt.mall.entity.UserInfo;
import com.xupt.mall.mapper.OrderDetailMapper;
import com.xupt.mall.service.OrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.service.OrderInfoService;
import com.xupt.mall.service.UserInfoService;
import com.xupt.mall.vo.OrderResVo;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {


    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public Boolean saveOrderDetail(OrderDetail orderDetail) {

        int insert = baseMapper.insert(orderDetail);
        if(insert == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<OrderResVo> listOrderDetail(Integer userId) {

        List<OrderInfo> orderInfoList = orderInfoService.listOrderInfoByUserId(userId);
        List<OrderResVo> orderResVoList = new ArrayList<>();

        if(orderInfoList != null && orderInfoList.size() > 0){
            for (OrderInfo orderInfo : orderInfoList) {
                QueryWrapper<OrderDetail> orderDetailQueryWrapper = new QueryWrapper<>();
                orderDetailQueryWrapper.eq("ORDER_ID",orderInfo.getId());
                OrderDetail orderDetail = baseMapper.selectOne(orderDetailQueryWrapper);
                OrderResVo orderResVo = new OrderResVo();


                UserInfo userInfo = userInfoService.getUserInfoById(userId);
                orderResVo.setNickName(userInfo.getNickName());
                orderResVo.setCreateTime(orderInfo.getCreateTime());
                orderResVo.setExpireTime(orderInfo.getExpireTime());
                orderResVo.setAddress(orderInfo.getAddress());
                orderResVo.setOrderStatus(orderInfo.getOrderStatus());
                orderResVo.setTotalAmount(orderInfo.getTotalAmount());
                orderResVo.setId(orderInfo.getId());
                orderResVo.setUserId(userId);

                if(orderDetail != null){
                    orderResVo.setSkuName(orderDetail.getSkuName());
                    orderResVo.setSkuNum(orderDetail.getSkuNum());
                    orderResVo.setOrderDetailId(orderDetail.getId());
                    orderResVo.setSkuId(orderDetail.getSkuId());
                }



                orderResVoList.add(orderResVo);
            }
        }

        return orderResVoList;
    }

    @Override
    public Boolean deleteByOrderId(List<OrderInfo> orderInfoList) {



        for (OrderInfo orderInfo : orderInfoList) {
            Integer orderInfoId = orderInfo.getId();
            UpdateWrapper<OrderDetail> orderDetailUpdateWrapper = new UpdateWrapper<>();
            orderDetailUpdateWrapper.eq("ORDER_ID",orderInfoId);
            int delete = baseMapper.delete(orderDetailUpdateWrapper);
        }

        return true;
    }
}
