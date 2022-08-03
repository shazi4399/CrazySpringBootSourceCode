package org.crazyit.firstboot.service;

import org.crazyit.firstboot.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

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
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class BookServiceTest
{
	@Autowired
	private BookService bookService;

	@Test
	public void testGetAllBooks()
	{
		bookService.getAllBooks().forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"疯狂Java讲义, 李刚, 129.0", "疯狂Android讲义, 李刚, 128.0"})
	public void testAddBook(String title, String author, double price)
	{
		var book = new Book(title, author, price);
		Integer result = bookService.addBook(book);
		System.out.println(result);
		Assertions.assertNotEquals(result, 0);
	}

	@ParameterizedTest
	@ValueSource(ints = {9, 10})
	public void testDeleteBook(Integer id)
	{
		bookService.deleteBook(id);
	}
}
