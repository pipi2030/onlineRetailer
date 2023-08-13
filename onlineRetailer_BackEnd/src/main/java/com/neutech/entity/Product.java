package com.neutech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product  {
    @TableId
    private Integer productId;
    private Integer typeId;
    private String productName;
    private BigDecimal productPrice;
    private String productImage;
    private Integer productStock;
    private Integer sellCount;
    private Integer isRecommend;
    private Date createTime;
}
