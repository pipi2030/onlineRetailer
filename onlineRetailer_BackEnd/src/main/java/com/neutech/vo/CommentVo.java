package com.neutech.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {
    private Integer commentId;
    private String UserImage;//new
//    private Integer productId;
    private String UserName;//new
//    private Integer userId;
    private String content;
    private Integer score;
//    @JsonFormat(pattern = "yyyy年MM月dd日 HH时mm分", timezone = "GMT+8")
//    采用自定义的时间格式
    private String commentTime;
}
