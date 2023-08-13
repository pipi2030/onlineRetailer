package com.neutech.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neutech.entity.Product;
import com.neutech.entity.ProductType;
import org.apache.ibatis.annotations.CacheNamespace;

public interface ProductMapper extends BaseMapper<Product> {
}
