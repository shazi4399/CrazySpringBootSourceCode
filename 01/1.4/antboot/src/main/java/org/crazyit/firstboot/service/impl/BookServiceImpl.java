package org.crazyit.firstboot.service.impl;

import org.crazyit.firstboot.dao.BookDao;
import org.crazyit.firstboot.service.BookService;
import org.crazyit.firstboot.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@Transactional(propagation = Propagation.REQUIRED, timeout = 5)
public class BookServiceImpl implements BookService
{
	// 依赖注入容器中BookDao组件
	@Autowired
	private BookDao bookDao;
	@Override
	public List<Book> getAllBooks()
	{
		return (List<Book>) bookDao.findAll();
	}

	@Override
	public Integer addBook(Book book)
	{
		bookDao.save(book);
		return book.getId();
	}

	@Override
	public void deleteBook(Integer id)
	{
		bookDao.deleteById(id);
	}
}
