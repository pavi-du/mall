package com.xupt.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
    private String userId;

    @TableField("ORDER_STATUS")
    private String orderStatus;

    @TableField("ADDRESS")
    private String address;

    @TableField("CREAET_TIME")
    private Date creaetTime;

    @TableField("EXPIRE_TIME")
    private Date expireTime;

    @TableField("IMG_URL")
    private String imgUrl;


}
