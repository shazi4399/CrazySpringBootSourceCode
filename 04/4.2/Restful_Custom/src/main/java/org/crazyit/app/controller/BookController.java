package org.crazyit.app.controller;

import org.crazyit.app.domain.Book;
import org.crazyit.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
@RestController
@RequestMapping("/book")
public class BookController
{
	@Autowired
	private BookService bookService;

	@PostMapping("")
	public Book create(@RequestBody Book book)
	{
		return this.bookService.createOrUpdate(book);
	}

	@PutMapping("")
	public Book update(@RequestBody Book book)
	{
		Objects.requireNonNull(book);
		return this.bookService.createOrUpdate(book);
	}

	@GetMapping("")
	public Collection<Book> list()
	{
		return this.bookService.list();
	}
}