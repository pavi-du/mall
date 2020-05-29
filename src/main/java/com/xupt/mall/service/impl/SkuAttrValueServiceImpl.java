package com.xupt.mall.service.impl;

import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.SkuAttrValue;
import com.xupt.mall.mapper.SkuAttrValueMapper;
import com.xupt.mall.service.SkuAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.vo.BaseAttrValueVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@CrossOrigin
@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {


    @Override
    public Boolean saveAttrValueList(List<String> baseAttrIdList, List<BaseAttr> baseAttrList, Integer skuInfoId) {

        for (String baseAttrId : baseAttrIdList) {
            for (BaseAttr baseAttr : baseAttrList) {
                if (String.valueOf(baseAttr.getId()).equals(baseAttrId)){
                    List<BaseAttrValueVO> baseAttrValueList = baseAttr.getBaseAttrValueList();
                    for (BaseAttrValueVO baseAttrValueVO : baseAttrValueList) {
                        SkuAttrValue skuAttrValue = new SkuAttrValue();
                        skuAttrValue.setSkuId(String.valueOf(skuInfoId));
                        skuAttrValue.setBaseAttrId(baseAttrId);
                        skuAttrValue.setBaseAttrValueId(String.valueOf(baseAttrValueVO.getId()));
                        int insert = baseMapper.insert(skuAttrValue);
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
