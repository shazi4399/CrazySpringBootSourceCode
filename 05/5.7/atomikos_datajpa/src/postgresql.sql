drop database if exists springboot;
create database springboot;

\c springboot;

-- 创建数据表;
create table news_inf
(
 news_id serial primary key,
 news_title varchar(255) not null,
 news_content varchar(255)
);

insert into news_inf (news_title, news_content)
values ('11', '1111111111111');
insert into news_inf (news_title, news_content)
values ('22', '2222222222222');
insert into news_inf (news_title, news_content)
values ('33', '3333333333333');
insert into news_inf (news_title, news_content)
values ('44', '4444444444444');
insert into news_inf (news_title, news_content)
values ('55', '5555555555555');
insert into news_inf (news_title, news_content)
values ('66', '6666666666666');
insert into news_inf (news_title, news_content)
values ('77', '7777777777777');
