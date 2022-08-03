package org.crazyit.app.service.impl;

import org.crazyit.app.domain.Book;
import org.crazyit.app.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@Service
public class BookServiceImpl implements BookService
{
	static List<Book> booksDb = List.of(
			new Book(1, "疯狂Java讲义", "java.png", "李刚", 128.0),
			new Book(2, "轻量级Java Web企业应用实战", "javaweb.png", "李刚", 138.0),
			new Book(3, "疯狂Android讲义", "android.png", "李刚", 138.0),
			new Book(4, "疯狂Python讲义", "python.png", "李刚", 128.0));
	@Override
	public Book getBookById(Integer id)
	{
		// 根据id来获取对应的图书
		return booksDb.stream().filter(b -> b.getId() == id).findFirst().get();
	}
}
