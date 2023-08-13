package com.neutech.controller;

import com.neutech.common.ServerResponse;
import com.neutech.entity.Comment;
import com.neutech.service.CommentService;
import com.neutech.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/getCommentList/{productId}")
    public ServerResponse getCommentList(@PathVariable int productId){
        List<CommentVo> commentList = commentService.getCommentList(productId);
        if(commentList.size() > 0){
            return ServerResponse.success().data("commentList",
                    commentList);
        }else{
            return ServerResponse.failed("暂时没有评论");
        }
    }
    @PostMapping("/addComment")
    public ServerResponse addComment(@RequestBody Comment comment){
        int i = commentService.addComment(comment);
        if(i == 1){
            return ServerResponse.success("").data("insert",i);
        }else{
            return ServerResponse.failed("插入失败");
        }
    }
}
