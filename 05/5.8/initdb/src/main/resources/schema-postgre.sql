drop table if exists user_inf;
-- 创建user_inf表
create table user_inf
(
  user_id serial primary key,
  name varchar(255) not null,
  password varchar(255),
  age int
);
