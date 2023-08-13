package com.neutech.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neutech.Utils.StringUtils;
import com.neutech.entity.Comment;
import com.neutech.entity.Product;
import com.neutech.entity.User;
import com.neutech.mapper.CommentMapper;
import com.neutech.mapper.ProductMapper;
import com.neutech.mapper.UserMapper;
import com.neutech.service.CommentService;
import com.neutech.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public CommentVo getCommentVo(int commentId) {
        CommentVo commentVo = new CommentVo();
        Comment comment = this.getById(commentId);
        BeanUtils.copyProperties(comment,commentVo);
        User user = userMapper.selectById(comment.getUserId());
        commentVo.setUserImage(user.getPhoto());
        commentVo.setUserName(user.getUserName());
        if (comment.getCommentTime() == null){
            commentVo.setCommentTime("无效时间");
        }else{
            commentVo.setCommentTime(StringUtils.convertFormat(comment.getCommentTime()));
        }
        return commentVo;
    }

    @Override
    public List<CommentVo> getCommentList(int productId) {
        ArrayList<CommentVo> commentList = new ArrayList<>();
        List<Comment> comments = this.query().eq("product_id",productId).list();
        for (Comment comment : comments) {
            CommentVo commentVo = this.getCommentVo(comment.getCommentId());
            commentList.add(commentVo);
        }
        return commentList;
    }

    @Override
    public int addComment(Comment comment) {
        comment.setCommentTime(new Date());
        return commentMapper.insert(comment);
    }
}
