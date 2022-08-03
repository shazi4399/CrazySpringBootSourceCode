package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;

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
	private SolrTemplate solrTemplate;

	@Override
	public List<Book> customQuery1(String name, String description)
	{
		// 定义查询语句
		Query query = Query.query("name:" + name +
				" AND description:" + description);
		// 调用SolrTemplate的方法执行查询
		return solrTemplate.query("springboot",
				query, Book.class).toList();
	}
}