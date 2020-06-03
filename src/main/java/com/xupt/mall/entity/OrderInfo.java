package com.xupt.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OrderInfo对象", description="")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("TOTAL_AMOUNT")
    private String totalAmount;

    @TableField("USER_ID")
    private Integer userId;

    @TableField("ORDER_STATUS")
    private String orderStatus;

    @TableField("ADDRESS")
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField("CREATE_TIME")
    private LocalDate createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @TableField("EXPIRE_TIME")
    private LocalDate expireTime;

    @TableField("IMG_URL")
    private String imgUrl;

    @TableField(exist = false)
    private String nickName;

    @TableField(exist = false)
    private String skuName;

    @TableField(exist = false)
    private Integer skuCount;

    @TableField(exist = false)
    private Integer skuId;


}
