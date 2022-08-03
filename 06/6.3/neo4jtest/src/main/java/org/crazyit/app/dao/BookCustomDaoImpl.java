package org.crazyit.app.dao;

import org.crazyit.app.domain.Author;
import org.crazyit.app.domain.Book;
import org.neo4j.driver.*;
import org.neo4j.driver.reactive.RxSession;
import org.neo4j.driver.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.ReactiveNeo4jTemplate;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	private ReactiveNeo4jTemplate neo4jTemplate;
	@Autowired
	private Driver driver;

	@Override
	public Flux<Book> customQuery1(String regex, int startPrice)
	{
		// 调用ReactiveNeo4jTemplate的findAll()方法执行CQL查询
		return neo4jTemplate.findAll("match (b :Book) where " +
						"b.title =~ $0 and b.price >= $1 return b",
				Map.of("0", regex, "1", startPrice), Book.class);
	}

	@Override
	public Flux<Book> customQuery2(int startPrice, int endPrice)
	{
		// 获取RxSession
		RxSession rxSession = driver.rxSession();
		// Flux的from方法用于将Publisher<Record>转换成Flux<Record>
		return Flux.from(
			// 调用RxSession执行查询
			rxSession.run("MATCH (b: Book)-[r]->(a) " +
				"WHERE b.price >= $startPrice and b.price <= $endPrice RETURN b,a",
				Values.parameters("startPrice", startPrice, "endPrice", endPrice))
				// 调用records()方法返回Publisher<Record>
				.records())
			// 将Flux<Record>映射成Flux<Book>
			.map(record -> {
				Node node = record.get("b").asNode();
				String title = node.get("title").asString();
				int price = node.get("price").asInt();
				long id = node.id();
				Book book = new Book(title, price);
				book.setId(id);
				Node authorNode = record.get("a").asNode();
				String name = authorNode.get("name").asString();
				String addr = authorNode.get("author_addr").asString();
				long authorId = authorNode.id();
				Author author = new Author(name, addr);
				author.setId(authorId);
				book.setAuthor(author);
				return book;
			});
	}
}