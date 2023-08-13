package com.neutech.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType implements Serializable {
    @TableId
    private Integer typeId;
    private String typeName;
    private String typeImage;
}
