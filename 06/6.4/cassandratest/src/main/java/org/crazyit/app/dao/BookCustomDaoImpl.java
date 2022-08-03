package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraTemplate;
import org.springframework.data.cassandra.core.cql.BeanPropertyRowMapper;
import org.springframework.data.cassandra.core.cql.ReactiveCqlTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import reactor.core.publisher.Flux;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2022, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
public class BookCustomDaoImpl implements BookCustomDao
{
	@Autowired
	private ReactiveCassandraTemplate cassandraTemplate;
	@Autowired
	private ReactiveCqlTemplate cqlTemplate;

	@Override
	public Flux<Book> customQuery1(String namePattern)
	{
		// 创建Query对象，根据name属性执行查询
		var query = Query.query(Criteria.where("name").like(namePattern));
		// 调用ReactiveCassandraTemplate的select方法执行查询
		return cassandraTemplate.select(query, Book.class);
	}

	@Override
	public Flux<Book> customQuery2(double startPrice, double endPrice)
	{
		// 调用ReactiveCqlTemplate的query方法执行CQL查询语句
		return cqlTemplate.query("select * from book_inf where " +
						"price >= ? and price <= ? allow filtering",
				// 使用BeanPropertyRowMapper将每行结果映射成Book对象
				new BeanPropertyRowMapper<>(Book.class), startPrice, endPrice);
	}
}