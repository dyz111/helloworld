package com.atguigu.boot.Service.impl;

import com.atguigu.boot.Service.UserService;
import com.atguigu.boot.mapper.UserMapper;
import com.atguigu.boot.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.Service.Impl
 * @Date: 2023/4/26 21:51
 * @Author
 * @Description:
 */


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);
        return user;
    }
}
