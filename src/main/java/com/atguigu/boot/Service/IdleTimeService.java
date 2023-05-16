package com.atguigu.boot.Service;

import com.atguigu.boot.pojo.IdleTime;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.Service
 * @Date: 2023/5/13 14:13
 * @Author
 * @Description:
 */
public interface IdleTimeService extends IService<IdleTime> {

    List<java.sql.Date> getSqlDate();

    List<java.sql.Date> getNextSqlDate();

    List<IdleTime> getIdleByDate(Integer userid, java.sql.Date sqlDate);

    void checkOrCreateIdleTimeByDateList(Integer userid, List<java.sql.Date> sqlDateList);

    List<IdleTime> getThisIdleBySqlDateList(Integer userid, List<java.sql.Date> sqlDateList);
}
