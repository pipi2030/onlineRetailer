package com.neutech.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Test
    public void test1(){
        commentService.getCommentList(1);
    }
    @Test
    public void test2(){
        commentService.getCommentList(1);
    }
}
