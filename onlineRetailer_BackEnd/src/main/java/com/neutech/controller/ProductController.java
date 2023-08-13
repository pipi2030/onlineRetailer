package com.neutech.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neutech.common.ServerResponse;
import com.neutech.entity.Product;
import com.neutech.service.ProductService;
import com.neutech.vo.ProductVo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${config.pageSize}")
    private int pageSize;
    @Autowired
    private ProductService producteService;
    @GetMapping("/selectRecommendList")
    public ServerResponse selectRecommendList(){
        if(producteService.selectRecommendList().size() > 0){
            return ServerResponse.success().data("RecommendProductList",
                    producteService.selectRecommendList());
        }
        else{
            return ServerResponse.failed("没有推荐商品");
        }
    }
    @GetMapping("/selectTopList")
    public ServerResponse selectTopList(){
        if(producteService.selectTopList().size() > 0){
            return ServerResponse.success().data("TopProductList",
                    producteService.selectTopList());
        }
        else{
            return ServerResponse.failed("没有热销商品");
        }
    }
    @GetMapping("/selectNewList")
    public ServerResponse selectNewList(){
        if(producteService.selectNewList().size() > 0){
            return ServerResponse.success().data("NewProductList",
                    producteService.selectNewList());
        }
        else{
            return ServerResponse.failed("暂无商品");
        }
    }
    @GetMapping("/selectTypeList/{typeId}")
    public ServerResponse selectTypeList(@PathVariable int typeId,
           @RequestParam(required = false,defaultValue = "1")int currentPage){
        Page<Product> productPage = producteService.selectTypeList(typeId, currentPage, pageSize);
        if(productPage.getSize() > 0){
            return ServerResponse.success().data("ProductList",
                    productPage);
        }
        else{
            return ServerResponse.failed("暂无该类型商品");
        }
    }

    //分页查询
    @GetMapping("/getProductPage")
    public ServerResponse getProductPage(@RequestParam(required = false,defaultValue = "")String productName,
                                         @RequestParam(required = false,defaultValue = "1")int currentPage
        ){
        Page<Product> productPage = producteService.getProductPage(productName, currentPage, pageSize);
        if(productPage.getSize() > 0){
            return ServerResponse.success().data("ProductPage",
                    productPage);
        }
        else{
            return ServerResponse.failed("暂无商品");
        }
    }
    @GetMapping("/getProductDetail/{productId}")
    public ServerResponse getProductDetail(@PathVariable int productId){
        ProductVo productVo = producteService.getProductVo(productId);
        if(productVo!= null){
            return ServerResponse.success().data("productVo",
                    productVo);
        } else{
            return ServerResponse.failed("暂无商品详情");
        }
    }
}
