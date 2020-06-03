package com.xupt.mall.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.Catalog;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author pavi
 * @date 2020/5/28 9:58
 */
@Data
public class UploadSkuInfo {

    private String skuName;

    private String skuPrice;

    private String skuCount;

    private List<Integer> catalogIdList = new ArrayList<>();


    private String skuImg;

    private String skuDesc;

    private List<Integer> baseAttrIdList = new ArrayList<>();
    private List<Integer> baseAttrValueIdList = new ArrayList<>();

    private List<Catalog> catalogLit = new ArrayList<>();

    private List<BaseAttr> baseAttrList = new ArrayList<>();


}
