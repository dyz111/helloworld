package com.atguigu.boot.mapper;

import com.atguigu.boot.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.mapper
 * @Date: 2023/4/26 21:48
 * @Author
 * @Description:
 */

@Mapper
@Repository
@Component
public interface UserMapper extends BaseMapper<User> {

}
