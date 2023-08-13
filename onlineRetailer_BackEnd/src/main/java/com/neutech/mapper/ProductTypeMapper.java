package com.neutech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neutech.entity.ProductType;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace
public interface ProductTypeMapper extends BaseMapper<ProductType> {
}
