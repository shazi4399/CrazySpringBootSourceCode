package org.crazyit.app.service.impl;

import org.crazyit.app.domain.Book;
import org.crazyit.app.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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
	private final Map<Integer, Book> data = new ConcurrentHashMap<>();
	private static final AtomicInteger idGenerator = new AtomicInteger(0);

	@Override
	public Collection<Book> list()
	{
		return this.data.values();
	}

	@Override
	public Book createOrUpdate(Book book)
	{
		// 修改图书
		if (book.getId() != null && data.containsKey(book.getId()))
		{
			this.data.put(book.getId(), book);
		}
		else
		{
			Integer id = idGenerator.incrementAndGet();
			book.setId(id);
			this.data.put(id, book);
		}
		return book;
	}
}
