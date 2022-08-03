package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
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
public interface BookDao extends CrudRepository<Book, Integer>,
		QueryByExampleExecutor<Book>, BookCustomDao
{
	List<Book> findByName(String name);
	List<Book> findByDescription(String subDesc);
}
