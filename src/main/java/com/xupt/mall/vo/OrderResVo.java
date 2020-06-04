package com.xupt.mall.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * TODO
 *
 * @author pavi
 * @date 2020/6/4 8:57
 */
@Data
public class OrderResVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderDetailId;

    private Integer skuId;

    private String skuName;

    private String imgUrl;

    private Integer skuNum;

    private Integer id;

    private String totalAmount;

    private Integer userId;

    private String orderStatus;

    private String address;

    private LocalDate createTime;

    private LocalDate expireTime;

    private String nickName;



}
