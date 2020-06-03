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
@ApiModel(value="SkuInfo对象", description="")
public class SkuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("SKU_NAME")
    private String skuName;

    @TableField("SKU_PRICE")
    private Integer skuPrice;

    @TableField("SKU_COUNT")
    private Integer skuCount;

    @TableField("CATALOG_ID")
    private Integer catalogId;

    @TableField("SKU_IMG")
    private String skuImg;

    @TableField("SKU_DESC")
    private String skuDesc;

    @TableField(exist = false)
    private String catalogName;


}
