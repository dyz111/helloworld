-- 删库建库
DROP
DATABASE IF EXISTS hello;
CREATE
DATABASE hello;
USE
hello;

--删表
DROP TABLE IF EXISTS User;

--创建表
CREATE TABLE User
(
    Id       int NOT NULL auto_increment,
    username varchar(255) UNIQUE,
    password varchar(255),
    PRIMARY KEY (`Id`) USING BTREE
)CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;