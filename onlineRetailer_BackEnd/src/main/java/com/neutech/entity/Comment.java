package com.neutech.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer productId;
    private Integer userId;
    private String content;
    private Integer score;
    private Date commentTime;
}
