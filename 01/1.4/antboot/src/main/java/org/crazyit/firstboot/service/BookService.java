package org.crazyit.firstboot.service;

import org.crazyit.firstboot.domain.Book;
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
public interface BookService
{
	List<Book> getAllBooks();
	Integer addBook(Book book);
	void deleteBook(Integer id);
}
