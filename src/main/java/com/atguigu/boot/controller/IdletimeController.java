package com.atguigu.boot.controller;

import com.atguigu.boot.Service.IdleTimeService;
import com.atguigu.boot.Service.UserService;
import com.atguigu.boot.pojo.IdleTime;
import com.atguigu.boot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.controller
 * @Date: 2023/5/13 14:17
 * @Author
 * @Description:
 */
@RestController
public class IdletimeController {

    @Autowired
    private UserService userService;

    @Autowired()
    private IdleTimeService idleTimeService;

    /**
     * @param username
     * @description: 查看本周空闲时段
     * @return: java.util.List<com.atguigu.boot.pojo.IdleTime>
     */
    @GetMapping("/idle")
    public List<IdleTime> getIdle(@RequestParam String username) {
        //判断username是否存在
        User user = userService.getUserByUsername(username);
        if (user == null) {
            System.out.println("用户不存在！！！");
            return null;
        }

        //拿到本周一到本周五的sql date， 存在sqlDateList里
        List<java.sql.Date> sqlDateList = idleTimeService.getSqlDate();

        //在数据库逐日查找，如果没有就新增该日记录
        idleTimeService.checkOrCreateIdleTimeByDateList(user.getId(), sqlDateList);

        //返回本周记录
        List<IdleTime> thisIdleList = idleTimeService.getThisIdleBySqlDateList(user.getId(), sqlDateList);

        return thisIdleList;
    }

    @GetMapping("/idle/nextWeek")
    public List<IdleTime> getNextIdle(@RequestParam String username) {
        //判断username是否存在
        User user = userService.getUserByUsername(username);
        if (user == null) {
            System.out.println("用户不存在！！！");
            return null;
        }

        //拿到下周一到本周五的sql date， 存在sqlNextDateList里
        List<java.sql.Date> sqlNextDateList = idleTimeService.getNextSqlDate();

        //在数据库逐日查找，如果没有就新增该日记录
        idleTimeService.checkOrCreateIdleTimeByDateList(user.getId(), sqlNextDateList);

        return null;


    }

    @PostMapping("/idle")
    public String addIdle(@RequestParam String username,
                          @RequestBody List<IdleTime> idleTimeList) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            System.out.println("用户不存在！！！");
        }

        //更新本周记录
        boolean isSave = idleTimeService.saveOrUpdateBatch(idleTimeList);
        if (isSave) {
            System.out.println("更新成功！！！");
            return "更新成功";
        } else {
            System.out.println("更新失败！！！");
            return "更新失败";
        }
    }
}
