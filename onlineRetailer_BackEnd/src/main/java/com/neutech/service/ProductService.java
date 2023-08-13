package com.neutech.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neutech.entity.Product;
import com.neutech.vo.ProductVo;

import java.util.List;

public interface ProductService extends IService<Product> {
    List<Product> selectRecommendList();
    List<Product> selectTopList();
    List<Product> selectNewList();
    Page<Product> selectTypeList(int typeId,int currentPage,int pageSize);
    Page<Product> getProductPage(String productName,int currentPage,int pageSize);
    ProductVo getProductVo(int productId);

}
