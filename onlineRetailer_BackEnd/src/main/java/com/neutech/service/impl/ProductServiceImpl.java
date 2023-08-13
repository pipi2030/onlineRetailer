package com.neutech.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neutech.entity.Product;
import com.neutech.entity.ProductType;
import com.neutech.mapper.ProductMapper;
import com.neutech.mapper.ProductTypeMapper;
import com.neutech.service.ProductService;
import com.neutech.vo.ProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public List<Product> selectRecommendList() {
//        QueryWrapper<Product> wrapper = new QueryWrapper<>();
//        wrapper.eq("is_recommend",1).last("limit 6");
//        return productMapper.selectList(wrapper);

        //继承实现类之后，可以采用下面的写法
        return this.query().eq("is_recommend",1).last("limit 6").list();
    }

    @Override
    public List<Product> selectTopList() {
        return this.query().orderByDesc("sell_count").last("limit 6").list();
    }

    @Override
    public List<Product> selectNewList() {
        return this.query().orderByDesc("create_time").last("limit 6").list();
    }

    @Override
    public Page<Product> selectTypeList(int typeId, int currentPage, int pageSize) {
        Page page = new Page(currentPage,pageSize);
        return this.query().eq("type_id",typeId).page(page);
    }

    @Override
    public Page<Product> getProductPage(String productName, int currentPage, int pageSize) {
        Page page = new Page(currentPage,pageSize);
        return this.query().like("product_name",productName).page(page);
    }

    @Override
    public ProductVo getProductVo(int productId) {
        //通过多次单表查询实现类似多表查询的效果
        ProductVo productVo = new ProductVo();
        Product product = productMapper.selectById(productId);
        if(product == null){
            return null;
        }
        //Vo对象多属性复制
        BeanUtils.copyProperties(product,productVo);

        //为Vo对象中product不包含的属性赋值
        ProductType productType = productTypeMapper.selectById(product.getTypeId());

        //Vo对象单属性复制
        productVo.setProductType(productType.getTypeName());
        return productVo;
    }


}
