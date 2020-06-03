package com.xupt.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@ApiModel(value="SkuSaleAttrValue对象", description="")
public class SkuSaleAttrValue implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("BASE_ATTR_ID")
    private Integer baseAttrId;

    @TableField("BASE_ATTR_VALUE_ID")
    private Integer baseAttrValueId;

    @TableField("BASE_ATTR_NAME")
    private String baseAttrName;

    @TableField("BASE_ATTR_VALUE")
    private String baseAttrValue;

    @TableField("SKU_ID")
    private Integer skuId;


}
