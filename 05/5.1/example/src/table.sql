drop database springboot;
create database springboot;
use springboot;
-- 创建clazz_inf表
create table clazz_inf
(
  clazz_code int primary key auto_increment,
  name varchar(255)
);
-- 创建student_inf表
create table student_inf
(
  student_id int primary key auto_increment,
  name varchar(255),
  age int,
  address varchar(255),
  gender char(2),
  clazz_code int,
  foreign key(clazz_code) references clazz_inf(clazz_code)
);
-- 向clazz_inf表插入数据
insert into clazz_inf
values
(null, '疯狂Java训练营'),
(null, '疯狂Java就业班'),
(null, '疯狂Java基础班'),
(null, '疯狂Java提高班');
-- 向student_inf表插入数据
insert into student_inf
values
(null, '孙悟空', 500, '花果山水帘洞', '男', 1),
(null, '牛魔王', 800, '积雷山摩云洞', '男', 1),
(null, '猪八戒', 600, '福陵山云栈洞', '男', 2),
(null, '沙和尚', 580, '流沙河', '男', 3),
(null, '白鼠精', 23, '陷空山无底洞',  '女', 2),
(null, '蜘蛛精', 18, '盘丝岭盘丝洞', '女', 4),
(null, '玉面狐狸', 21, '积雷山摩云洞', '女', 3),
(null, '杏仙', 19, '荆棘岭木仙庵', '女', 4);