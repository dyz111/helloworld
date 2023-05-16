package com.atguigu.boot.controller;

import com.atguigu.boot.Service.UserService;
import com.atguigu.boot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.controller
 * @Date: 2023/4/26 20:44
 * @Author
 * @Description:
 */

@RestController
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public User helloWorld(String username) {
        return userService.getUserByUsername(username);
    }
}
