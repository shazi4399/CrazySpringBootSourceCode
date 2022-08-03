package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

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
public interface SyncBookDao extends CrudRepository<Book, Long>,
		QueryByExampleExecutor<Book>, SyncBookCustomDao
{
	// like运算符只使用简单的通配符：*
	List<Book> findByTitleLike(String titlePattern);
	// Regex（或Matches）进行正则表达式匹配
	List<Book> findByTitleMatches(String regex);
	List<Book> findByTitleContaining(String subTitle);
	List<Book> findByPriceBetween(double start, double end);
	@Query("MATCH (b :Book) - [:WRITTEN_BY] -> () WHERE b.price >= $0 AND b.price <= $1 RETURN b")
	List<Book> findByQuery1(double start, double end);
	@Query("MATCH (b :Book) - [:WRITTEN_BY] -> () WHERE b.title =~ $0 RETURN b")
	List<Book> findByQuery2(String titlePattern);
}
