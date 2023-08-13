package com.neutech.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neutech.entity.ProductType;
import com.neutech.mapper.ProductTypeMapper;
import com.neutech.service.ProductTypeService;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper,ProductType> implements ProductTypeService {
}
