package com.atguigu.boot.mapper;

import com.atguigu.boot.pojo.IdleTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.mapper
 * @Date: 2023/5/13 14:31
 * @Author
 * @Description:
 */
@Mapper
@Component
public interface IdleTimeMapper extends BaseMapper<IdleTime> {
}
