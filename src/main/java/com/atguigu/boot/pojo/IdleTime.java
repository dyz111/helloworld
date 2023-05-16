package com.atguigu.boot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @BelongsProject: boot-01-helloworld-2
 * @BelongsPackage: com.atguigu.boot.pojo
 * @Date: 2023/5/13 16:42
 * @Author
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("idletime")
public class IdleTime {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("userid")
    private Integer userId;

    private Date date;

    private String time;

    private Boolean idle;
}
