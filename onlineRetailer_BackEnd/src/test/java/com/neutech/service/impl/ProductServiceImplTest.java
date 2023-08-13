package com.neutech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neutech.entity.Product;
import com.neutech.mapper.ProductMapper;
import com.neutech.service.ProductService;
import com.neutech.vo.ProductVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductService productService;
    @Test
    public void test(){
        ProductVo productVo = productService.getProductVo(1);
        System.out.println(productVo);
    }
}
