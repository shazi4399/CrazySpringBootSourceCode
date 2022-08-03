package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
public interface BookDao extends ReactiveCrudRepository<Book, Integer>, BookCustomDao
{
	// like运算符使用SQL的通配符：%
	Flux<Book> findByNameLike(String namePattern);
	Flux<Book> findByIdIn(List<Integer> list);
	Mono<Book> findByPrice(double price);
	@Query("select * from book_inf where price >= ?0 and price <= ?1 ALLOW FILTERING")
	Flux<Book> findByQuery1(double start, double end);
	@Query("select * from book_inf where name like ?0")
	Flux<Book> findByQuery2(String namePattern);
}
