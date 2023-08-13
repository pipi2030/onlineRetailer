package com.neutech.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neutech.entity.Comment;
import com.neutech.entity.User;
import com.neutech.mapper.CommentMapper;
import com.neutech.mapper.UserMapper;
import com.neutech.service.CommentService;
import com.neutech.service.UserService;
import com.neutech.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
