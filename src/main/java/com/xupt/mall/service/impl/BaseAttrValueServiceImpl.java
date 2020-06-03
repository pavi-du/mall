package com.xupt.mall.service.impl;

import com.xupt.mall.entity.BaseAttr;
import com.xupt.mall.entity.BaseAttrValue;
import com.xupt.mall.mapper.BaseAttrValueMapper;
import com.xupt.mall.service.BaseAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xupt.mall.vo.BaseAttrValueVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class BaseAttrValueServiceImpl extends ServiceImpl<BaseAttrValueMapper, BaseAttrValue> implements BaseAttrValueService {

    @Override
    public List<BaseAttrValue> listBaseAttrValue() {

        List<BaseAttrValue> baseAttrValueList = baseMapper.selectList(null);

        return baseAttrValueList;
    }

    @Override
    public Boolean addBaseAttrValue(BaseAttrValueVO baseAttrValueVO) {

        BaseAttrValue baseAttrValue = new BaseAttrValue();
        BeanUtils.copyProperties(baseAttrValueVO, baseAttrValue);
        baseAttrValue.setBaseAttrValue(baseAttrValueVO.getBaseAttrName());

        int insert = baseMapper.insert(baseAttrValue);

        if (insert == 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteBaseAttrValue(Integer id) {

        int count = baseMapper.deleteById(id);
        if (count == 1) {
            return true;
        }

        return false;
    }


}
