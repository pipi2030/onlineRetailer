package com.neutech.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductVo {
    private Integer productId;
    private String productName;
    private BigDecimal productPrice;
    private String productType;
    private String productImage;
    private Integer productStock;
    private Integer sellCount;
    private Date createTime;
}
