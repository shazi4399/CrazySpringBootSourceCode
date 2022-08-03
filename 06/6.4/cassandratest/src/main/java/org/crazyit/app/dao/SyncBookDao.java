package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

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
public interface SyncBookDao extends CrudRepository<Book, Integer>, SyncBookCustomDao
{
	// like运算符使用SQL的通配符：%
	List<Book> findByNameLike(String namePattern);
	List<Book> findByIdIn(List<Integer> list);
	Optional<Book> findByPrice(double price);
	@Query("select * from book_inf where price >= ?0 and price <= ?1 ALLOW FILTERING")
	List<Book> findByQuery1(double start, double end);
	@Query("select * from book_inf where name like ?0")
	List<Book> findByQuery2(String namePattern);
}
