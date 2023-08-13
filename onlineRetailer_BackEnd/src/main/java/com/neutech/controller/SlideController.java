package com.neutech.controller;

import com.neutech.common.ServerResponse;
import com.neutech.entity.Slide;
import com.neutech.service.CommentService;
import com.neutech.service.SlideService;
import com.neutech.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SlideController {
    @Autowired
    private SlideService slideService;
    @GetMapping("/getSlide")
    public ServerResponse getSlide(){
        List<Slide> slideList = slideService.list();
        if(slideList.size() > 0){
            return ServerResponse.success().data("slideList",
                    slideList);
        }else{
            return ServerResponse.failed("没有找到轮播图");
        }
    }
}
