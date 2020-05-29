package com.xupt.mall.service.impl;

import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.SkuAttrValue;
import com.xupt.mall.entity.SkuSaleAttrValue;
import com.xupt.mall.mapper.SkuSaleAttrValueMapper;
import com.xupt.mall.service.SkuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.vo.BaseAttrValueVO;
import org.springframework.stereotype.Service;

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
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueMapper, SkuSaleAttrValue> implements SkuSaleAttrValueService {

    @Override
    public Boolean saveSaleAttrValueList(List<String> baseAttrIdList, List<BaseAttr> baseAttrList, Integer skuInfoId) {
        for (String baseAttrId : baseAttrIdList) {
            for (BaseAttr baseAttr : baseAttrList) {
                if (String.valueOf(baseAttr.getId()).equals(baseAttrId)){
                    List<BaseAttrValueVO> baseAttrValueList = baseAttr.getBaseAttrValueList();
                    for (BaseAttrValueVO baseAttrValueVO : baseAttrValueList) {
                        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
                        skuSaleAttrValue.setSkuId(String.valueOf(skuInfoId));
                        skuSaleAttrValue.setBaseAttrId(baseAttrId);

                        skuSaleAttrValue.setBaseAttrName(baseAttr.getBaseAttrName());
                        skuSaleAttrValue.setBaseAttrValue(baseAttrValueVO.getBaseAttrName());


                        skuSaleAttrValue.setBaseAttrValueId(String.valueOf(baseAttrValueVO.getId()));
                        int insert = baseMapper.insert(skuSaleAttrValue);
                        if(insert != 1){
                            return false;
                        }
                    }
                }

            }
        }
        return true;
    }
}
