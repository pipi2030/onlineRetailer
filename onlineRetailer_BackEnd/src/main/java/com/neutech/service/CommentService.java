package com.neutech.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neutech.entity.Comment;
import com.neutech.vo.CommentVo;

import java.util.List;


public interface CommentService extends IService<Comment> {
    CommentVo getCommentVo(int commentId);
    List<CommentVo> getCommentList(int productId);
    int addComment(Comment comment);
}
