package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.BeanPropertyRowMapper;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;

import java.util.List;

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
public class SyncBookCustomDaoImpl implements SyncBookCustomDao
{
	@Autowired
	private CassandraTemplate cassandraTemplate;
	@Autowired
	private CqlTemplate cqlTemplate;

	@Override
	public List<Book> customQuery1(String namePattern)
	{
		var query = Query.query(Criteria.where("name").like(namePattern));
		return cassandraTemplate.select(query, Book.class);
	}

	@Override
	public List<Book> customQuery2(double startPrice, double endPrice)
	{
		return cqlTemplate.query("select * from book_inf where price >= ? and price <= ? allow filtering",
				new BeanPropertyRowMapper<>(Book.class), startPrice, endPrice);
	}
}