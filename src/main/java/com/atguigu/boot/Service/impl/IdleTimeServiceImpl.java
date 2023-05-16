package com.atguigu.boot.Service.impl;

import com.atguigu.boot.Service.IdleTimeService;
import com.atguigu.boot.Service.UserService;
import com.atguigu.boot.mapper.IdleTimeMapper;
import com.atguigu.boot.pojo.IdleTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.Service.impl
 * @Date: 2023/5/13 14:32
 * @Author
 * @Description:
 */
@Service
public class IdleTimeServiceImpl extends ServiceImpl<IdleTimeMapper, IdleTime> implements IdleTimeService{


    @Autowired
    private UserService userService;

    @Autowired
    private IdleTimeMapper idleTimeMapper;

    @Override
    public List<Date> getSqlDate() {
        List<java.sql.Date> calList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
//        cal.set(2023, 4, 29);
        java.sql.Date sqlStartDate = new Date(cal.getTime().getTime());
        calList.add(sqlStartDate);

        for (int i = Calendar.TUESDAY; i <= Calendar.FRIDAY; i++) {
            cal.add(Calendar.DAY_OF_WEEK, 1);
            java.sql.Date date = new Date(cal.getTime().getTime());
            calList.add(date);

        }
        System.out.println(calList);
        return calList;
    }

    @Override
    public List<Date> getNextSqlDate() {

        List<java.sql.Date> calList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
//        cal.set(2023, 4, 29); // 2023-05-29 -- 2023-06-02  ||  2023-06-05 -- 2023-06-09
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        for (int i = Calendar.MONDAY; i <= Calendar.FRIDAY; i++) {

            java.sql.Date sqlDate = new Date(cal.getTime().getTime());
            calList.add(sqlDate);
            cal.add(Calendar.DAY_OF_WEEK, 1);
        }
        System.out.println(calList);
        return calList;
    }


    /**
     * @description: 通过一条date找数据库中有没有该日的数据
     * @param sqlDate
     * @return: com.atguigu.boot.pojo.IdleTime
     */
    @Override
    public List<IdleTime> getIdleByDate(Integer userid, java.sql.Date sqlDate) {
        QueryWrapper<IdleTime> wrapper = new QueryWrapper<>();
        wrapper.eq("userid", userid).eq("date", sqlDate);
        List<IdleTime> idleTime = idleTimeMapper.selectList(wrapper);
        return idleTime;
    }


    @Override
    public void checkOrCreateIdleTimeByDateList(Integer userid, List<Date> sqlDateList) {
        for (java.sql.Date sqlDate : sqlDateList) {
            //判断有没有该日数据
            List<IdleTime> idle = getIdleByDate(userid, sqlDate);
            System.out.println(idle);
            if (idle.isEmpty()) {
                System.out.println("没有该日数据！！！");
                //创建一个数组，存放所有的time值
                String[] times = {"9:00-10:00", "10:00-11:00", "11:00-12:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00", "19:00-20:00", "20:00-21:00"};
                //遍历这个数组
                for (String time : times) {
                    //创建一个IdleTime对象
                    IdleTime idleTime = new IdleTime();
                    //给它的属性赋值
                    idleTime.setUserId(userid);
                    idleTime.setDate(sqlDate);
                    idleTime.setTime(time);
                    if ((time.equals("19:00-20:00") || time.equals("20:00-21:00"))) {
                        idleTime.setIdle(true);
                    } else {
                        idleTime.setIdle(false);
                    }
                    //调用save方法插入数据
                    save(idleTime);
                    if (getIdleByDate(userid, sqlDate) != null) {
                        System.out.println("插入成功！！！");
                    } else {
                        System.out.println("插入失败！！！");
                    }
                }
            }

        }
    }

    /**
     * @description:
     * @param userid
     * @param sqlDateList
     * @return: java.util.List<com.atguigu.boot.pojo.IdleTime>
     */
    @Override
    public List<IdleTime> getThisIdleBySqlDateList(Integer userid, List<java.sql.Date> sqlDateList) {
        List<IdleTime> idleList = new ArrayList<>();
        for (java.sql.Date date : sqlDateList) {
            QueryWrapper<IdleTime> wrapper = new QueryWrapper<>();
            wrapper.eq("userid", userid).eq("date", date);
            List<IdleTime> idles = idleTimeMapper.selectList(wrapper);
            idleList.addAll(idles);
        }

        return idleList;
    }
}
