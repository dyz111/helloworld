package com.atguigu.boot.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

/**
 * @discription: 对应于数据库中的 User 表
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName(value = "user")
public class User {

    @TableField("id")
    @JsonIgnore
    private Integer id;

    @TableField("username")
    private String username;

    private String password;

    private Integer age;

    private String email;

}