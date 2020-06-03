package com.xupt.mall.service.impl;

import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.BaseAttrValue;
import com.xupt.mall.mapper.BaseAttrMapper;
import com.xupt.mall.service.BaseAttrService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.service.BaseAttrValueService;
import com.xupt.mall.vo.BaseAttrValueVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-25
 */
@Service
public class BaseAttrServiceImpl extends ServiceImpl<BaseAttrMapper, BaseAttr> implements BaseAttrService {

    @Autowired
    private BaseAttrValueService baseAttrValueService;

    @Override
    public List<BaseAttr> listBaseAttr() {

        List<BaseAttr> baseAttrList = baseMapper.selectList(null);
        List<BaseAttrValue> baseAttrValueList = baseAttrValueService.listBaseAttrValue();

        if (baseAttrList != null) {
            for (BaseAttr baseAttr : baseAttrList) {
                List<BaseAttrValueVO> tempBaseAttrValueList = new ArrayList<>();
                for (BaseAttrValue baseAttrValue : baseAttrValueList) {
                    //System.out.println(baseAttrValue.getBaseAttrId()+"---"+baseAttr.getId());
                    if(String.valueOf(baseAttrValue.getBaseAttrId()).equals(String.valueOf(baseAttr.getId()))){

                        BaseAttrValueVO baseAttrValueVO = new BaseAttrValueVO();
                        BeanUtils.copyProperties(baseAttrValue,baseAttrValueVO);
                        baseAttrValueVO.setBaseAttrName(baseAttrValue.getBaseAttrValue());
                        tempBaseAttrValueList.add(baseAttrValueVO);
                    }
                }
                baseAttr.setBaseAttrValueList(tempBaseAttrValueList);
            }
        }

        return baseAttrList;
    }


}
