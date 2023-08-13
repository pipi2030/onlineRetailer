package com.neutech.controller;

import com.neutech.common.ServerResponse;
import com.neutech.entity.ProductType;
import com.neutech.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productType")
public class ProductTypeController {
    @Autowired
    private ProductTypeService productTypeService;
    @GetMapping("/list")
    public ServerResponse list(){
        List<ProductType> productTypeList = productTypeService.list();
        if(productTypeList.size() > 0){
            return ServerResponse.success("查找成功").data("productTypeList",productTypeList);
        }
        else{
            return ServerResponse.failed("没有任何商品类型");
        }
    }

}
