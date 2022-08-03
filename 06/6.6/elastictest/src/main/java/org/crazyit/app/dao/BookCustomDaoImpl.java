package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;

import java.util.ArrayList;
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
public class BookCustomDaoImpl implements BookCustomDao
{
	@Autowired
	private ElasticsearchRestTemplate restTemplate;

	@Override
	public List<Book> customQuery1(String name, String description)
	{
		// 以面向对象的方式定义查询语句
		Criteria criteria = new Criteria("name").is(name)
				.and("description").is(description);
		// 创建CriteriaQuery
		Query query = new CriteriaQuery(criteria);
		SearchHits<Book> hits =  restTemplate.search(query, Book.class);
		List<Book> books = new ArrayList<>();
		hits.forEach(hit -> books.add(hit.getContent()));
		return books;
	}
}