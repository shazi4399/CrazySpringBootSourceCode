package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.data.mongodb.repository.Query;
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
public interface SyncBookDao extends CrudRepository<Book, String>,
		QueryByExampleExecutor<Book>, SyncBookCustomDao
{
	// like运算符只使用简单的通配符：*
	List<Book> findByNameLike(String namePattern);
	// Regex进行正则表达式匹配
	List<Book> findByNameRegex(String regex);
	List<Book> findByDescriptionContains(String subDesc);
	List<Book> findByPriceBetween(double start, double end);
	@Query("{price: {$gt: ?0, $lt: ?1}}")
	List<Book> findByQuery1(double start, double end);
	@Query("{name: {$regex: ?0}}")
	List<Book> findByQuery2(String namePattern);
}
