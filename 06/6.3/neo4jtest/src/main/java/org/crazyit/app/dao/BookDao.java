package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
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
public interface BookDao extends ReactiveCrudRepository<Book, Long>,
		ReactiveQueryByExampleExecutor<Book>, BookCustomDao
{
	// Like关键字只使用简单的通配符：*
	Flux<Book> findByTitleLike(String titlePattern);
	// Regex（或Matches）进行正则表达式匹配
	Flux<Book> findByTitleMatches(String regex);
	Flux<Book> findByTitleContains(String subTitle);
	Flux<Book> findByPriceBetween(double start, double end);
	@Query("MATCH (b :Book) - [:WRITTEN_BY] -> () WHERE " +
			"b.price >= $0 AND b.price <= $1 RETURN b")
	Flux<Book> findByQuery1(double start, double end);
	@Query("MATCH (b :Book) - [:WRITTEN_BY] -> () " +
			"WHERE b.title =~ $0 RETURN b")
	Flux<Book> findByQuery2(String titlePattern);
}