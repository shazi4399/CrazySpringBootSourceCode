package org.crazyit.app;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.crazyit.app.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.solr.core.SolrTemplate;

import java.io.IOException;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SolrClientTest
{
	@Autowired
	private SolrClient solrClient;

	@ParameterizedTest
	@CsvSource({"1, 疯狂Java讲义, 最全面深入的Java图书, 129.0",
			"2, SpringBoot终极讲义, 无与伦比的SpringBoot热点图书, 119.0",
			"3, 疯狂Python, 系统易懂的Python图书，覆盖数据分析、爬虫等全部热门内容, 118.0"})
	public void testSave(Integer id, String name,
			String description, Double price) throws IOException, SolrServerException
	{
		var book = new Book(id, name, description, price);
		// 指定向springboot Core保存文档
		// 调用addBeans()方法则可保存多个文档
		solrClient.addBean("springboot", book, 100);
	}

	@ParameterizedTest
	@CsvSource({"name, 疯狂",
			"description,热*"})
	public void testQuery(String field, String term) throws IOException, SolrServerException
	{
		// 创建SolrQuery
		SolrQuery query = new SolrQuery();
		// 设置查询语法
		query.setQuery(field + ":" + term);
		// 指定对springboot Core执行查询
		QueryResponse queryResponse = solrClient.query("springboot", query);
		// 获取查询结果
		SolrDocumentList docs = queryResponse.getResults();
		for (var doc : docs)
		{
			System.out.println(doc);
		}
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "2", "3"})
	public void testDelete(String id) throws IOException, SolrServerException
	{
		// 指定对springboot Core执行删除
		solrClient.deleteById("springboot", id, 100);
	}
}