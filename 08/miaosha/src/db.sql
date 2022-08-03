drop database miaosha_app;
create database miaosha_app;
use miaosha_app;

-- 秒杀用户表
drop table if exists user_inf;
create table user_inf
(
  user_id bigint primary key comment '手机号码作为用户ID',
  nickname varchar(255) not null,
  password varchar(32) comment '保存加盐加密后的密码：MD5(密码, salt)',
  salt varchar(10),
  head varchar(128) comment '头像地址',
  register_date datetime comment '注册时间',
  last_login_date datetime comment '上次登录时间',
  login_count int comment '登录次数'
) comment='秒杀用户表';

insert into user_inf values
(13500008888, 'fkjava', '7fb8f847c09ad032fbf3e3b9fcd2101f', '0p9o8i', null, curdate(), curdate(), 1),
(13500006666, 'fkit', '7fb8f847c09ad032fbf3e3b9fcd2101f', '0p9o8i', null, curdate(), curdate(), 1),
(13500009999, 'crazyit', '7fb8f847c09ad032fbf3e3b9fcd2101f', '0p9o8i', null, curdate(), curdate(), 1);

--  商品表
drop table if exists item_inf;
create table item_inf
(
  item_id bigint primary key auto_increment comment '商品ID',
  item_name varchar(255) comment '商品名称',
  title varchar(64) comment '商品标题',
  item_img varchar(64) comment '商品的图片',
  item_detail longtext comment '商品的详情介绍',
  item_price decimal(10,2) comment '商品单价',
  stock_num int comment '商品库存,-1表示没有限制'
) comment='商品表';

insert into item_inf values
(1, '疯狂Java讲义', '行销几十万册，成为海峡两岸读者之选，赠送20+小时视频、源代码、课件、面试题，微信交流答疑群', 'books/java.png', '1）作者提供用于学习和交流的配套网站及作者亲自在线的微信群、QQ群。<br>2）《疯狂Java讲义》历时十年沉淀，现已升级到第5版，经过无数Java学习者的反复验证，被包括北京大学在内的大量985、211高校的优秀教师引荐为参考资料、选作教材。<br>3）《疯狂Java讲义》曾翻译为中文繁体字版，在宝岛台湾上市发行。<br>4）《疯狂Java讲义》屡获殊荣，多次获取电子工业出版社的“畅销图书”、“长销图书”奖项，作者本人也多次获得“优秀作者”称号。仅第3版一版的印量即达9万多册。', 139.00, 2000);
insert into item_inf values
(2, '轻量级Java Web企业应用实战——Spring MVC+Spring+MyBatis整合开发', '源码级剖析Spring框架，适合已掌握Java基础或学完疯狂Java讲义的读者，送配套代码、100分钟课程。进微信群', 'books/javaweb.png', '《轻量级Java Web企业应用实战――Spring MVC+Spring+MyBatis整合开发》不是一份“X天精通Java EE开发”的“心灵鸡汤”，这是一本令人生畏的“砖头”书。<h4>1. 内容实际，针对性强</h3>本书介绍的Java EE应用示例，采用了目前企业流行的开发架构，严格遵守Java EE开发规范，而不是将各种技术杂乱地糅合在一起号称Java EE。读者参考本书的架构，完全可以身临其境地感受企业实际开发。<h4>2．框架源代码级的讲解，深入透彻</h3>
本书针对Spring MVC、Spring、MyBatis框架核心部分的源代码进行了讲解，不仅能帮助读者真正掌握框架的本质，而且能让读者参考优秀框架的源代码快速提高自己的技术功底。本书介绍的源代码解读方法还可消除开发者对阅读框架源代码的恐惧，让开发者在遇到技术问题时能冷静分析问题，从框架源代码层次找到问题根源。<h4>3．丰富、翔实的代码，面向实战</h3>本书是面向实战的技术图书，坚信所有知识点必须转换成代码才能最终变成有效的生产力，因此本书为所有知识点提供了对应的可执行的示例代码。代码不仅有细致的注释，还结合理论对示例进行了详细的解释，真正让读者做到学以致用。', 139.00, 2300);
insert into item_inf values
(3, '疯狂Android讲义', 'Java语言实现，安卓经典之作，stormzhang刘望舒柯俊林启舰联合力荐，曾获评CSDN年度具有技术影响力十大原创图书', 'books/android.png', '<ul><li>《疯狂Android讲义》自面市以来重印30+次，发行量近20万册，并屡获殊荣！</li><li>开卷数据显示《疯狂Android讲义》曾位列Android图书年度排行榜三甲</li><li>《疯狂Android讲义》曾获评CSDN年度具有技术影响力十大原创图书</li><li>青年意见领袖StormZhang及多部Android牛书作者刘望舒、柯俊林、启舰联合力荐</li><li>多次荣获电子工业出版社年度畅销图书及长销图书大奖</li><li>被工信出版集团授予年度“优秀出版物”奖</li></ul>', 138.00, 2300);
insert into item_inf values
(4, '疯狂HTML 5/CSS3/JavaScript讲义', 'HTML 5与JavaScript编程的经典制作，前端开发的必备基础', 'books/html.png', '知名IT作家李刚老师力作，全书面向HTML5.1规范正式版，更新多个元素、拖放规范的相关知识，新增外挂字幕、点线模式等内容，着重介绍新增的手机端相关特性<br>详细介绍渐变背景支持、弹性盒布局、手机浏览器响应式布局、3D变换等CSS新增特性及重大改进', 99.00, 2300);
insert into item_inf values
(5, '疯狂Python讲义', '零基础学Python编程实战，CSDN爆款Python课程指定用书，覆盖爬虫、大数据、并发编程等就业热点，Python求职不再慌', 'books/python.png', '<ul><li>CSDN爆款课程“21天通关Python”指定用书。</li><li>京东科技IT新书榜探花之作，入选2019年度京东科技IT榜畅销榜</li><li>上手门槛低，8岁的小朋友Charlie亲验，不但可以看懂书中关于Python语法的基础知识，且写出了自己的小程序。</li><li>覆盖的知识面广，知识体系完备、系统，再也不用“面向百度”编程。</li></ul>', 118.00, 2300);
insert into item_inf values
(6, '轻量级Java EE企业应用实战——Struts 2+Spring+Hibernate5/JPA2整合开发', 'S2SH经典图书升级版，全面拥抱Spring 5轻量级Web开发新特性；面世十余年，历经数十万读者检验；', 'books/javaee.png', '<h4>1. 图书的附加值超燃</h3>DVD光盘中包含1000分钟超长视频、丰富代码等内容。<br>为读者提供用于学习交流的配套网站、微信群、QQ群。附赠107道各大企业Java EE面试题，覆盖Java Web、Struts 2、Hibernate、Spring、Spring MVC，助力叩开名企Java开发大门。<h4>2. 屡获殊荣</h3>本书曾荣获中国书刊发行业协会授予的“年度全行业YouXiu畅销品种”奖项，并多次荣获电子工业出版社授予的畅销书奖项，累计印刷40+次。', 139.00, 2300);

-- 秒杀商品表
drop table if exists miaosha_item;
create table miaosha_item
(
  miaosha_id bigint primary key auto_increment comment '秒杀的商品表',
  item_id bigint comment '商品ID',
  miaosha_price decimal(10,2) comment '秒杀价',
  stock_count int comment '库存数量',
  start_date datetime comment '秒杀开始时间',
  end_date datetime comment '秒杀结束时间',
  foreign key(item_id) references item_inf(item_id)
) comment='秒杀商品表';

insert into miaosha_item values (1, 1, 1.98, 8, adddate(curdate(), -1), adddate(curdate(), 3));
insert into miaosha_item values (2, 2, 2.98, 8, adddate(curdate(), -1), adddate(curdate(), 2));
insert into miaosha_item values (3, 3, 3.98, 8, adddate(curdate(), -3), adddate(curdate(), -1));
insert into miaosha_item values (4, 4, 4.98, 8, adddate(curdate(), 1), adddate(curdate(), 5));
insert into miaosha_item values (5, 5, 5.98, 8, adddate(curdate(), -1), adddate(curdate(), 2));
insert into miaosha_item values (6, 6, 6.98, 8, adddate(curdate(), -1), adddate(curdate(), 2));

-- 订单表
drop table if exists order_inf;
create table order_inf
(
  order_id bigint primary key auto_increment,
  user_id bigint comment '用户ID',
  item_id bigint comment '商品ID',
  item_name varchar(255) comment '冗余的商品名称，用于避免多表连接',
  order_num int comment '购买的商品数量',
  order_price decimal(10,2) comment '购买价格',
  order_channel tinyint comment '渠道：1、PC, 2、Android, 3、iOS',
  order_status tinyint comment '订单状态,0新建未支付, 1已支付,2已发货, 3已收货, 4已退款,5已完成',
  create_date datetime comment '订单的创建时间',
  pay_date datetime comment '支付时间',
  foreign key(user_id) references user_inf(user_id),
  foreign key(item_id) references item_inf(item_id)
)  comment='订单表';

-- 秒杀订单表
drop table if exists miaosha_order;
create table miaosha_order
(
  miaosha_order_id bigint primary key auto_increment,
  user_id bigint comment '用户ID',
  order_id bigint comment '订单ID',
  item_id bigint comment '商品ID',
  unique key(user_id, item_id),
  foreign key(user_id) references user_inf(user_id),
  foreign key(order_id) references order_inf(order_id),
  foreign key(item_id) references item_inf(item_id)
) comment='秒杀订单表';
