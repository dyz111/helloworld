package com.atguigu.boot.Service;

import com.atguigu.boot.pojo.User;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.Service
 * @Date: 2023/4/26 21:50
 * @Author
 * @Description:
 */
public interface UserService {
    User getUserByUsername(String username);
}
