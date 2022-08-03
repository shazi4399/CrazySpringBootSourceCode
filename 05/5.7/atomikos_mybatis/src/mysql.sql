drop database springboot;
create database springboot;
use springboot;

-- 创建user_inf表
create table user_inf
(
  user_id int primary key auto_increment,
  name varchar(255) not null,
  password varchar(255),
  age int
);
-- 向user_inf表插入数据
insert into user_inf
values
(null, '孙悟空', 'sun123', 500),
(null, '牛魔王', 'niu123', 800),
(null, '猪八戒', 'zhu123', 600),
(null, '沙和尚', 'sha123', 580),
(null, '白鼠精', 'bai123', 23),
(null, '蜘蛛精', 'zhi123', 18),
(null, '玉面狐狸', 'yumian123', 21),
(null, '杏仙', 'xing123', 19);
