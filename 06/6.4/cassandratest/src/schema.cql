create keyspace if not exists springboot
with replication = {'class': 'SimpleStrategy', 'replication_factor': '3'};

use springboot;

create table if not exists book_inf
(
id int primary key,
name text,
description text,
price double
);

-- 创建索引
create custom index ON book_inf (name)
USING 'org.apache.cassandra.index.sasi.SASIIndex'
WITH OPTIONS ={
'mode': 'CONTAINS',
'analyzer_class': 'org.apache.cassandra.index.sasi.analyzer.StandardAnalyzer',
'case_sensitive': 'false'
};
create index on book_inf (price);

create table if not exists author_inf
(
id int,
name text,
password text,
author_addr text,
primary key(id, name, password)
)WITH CLUSTERING ORDER BY (name ASC, password DESC);