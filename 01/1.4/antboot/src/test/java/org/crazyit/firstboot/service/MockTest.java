package org.crazyit.firstboot.service;

import org.crazyit.firstboot.dao.BookDao;
import org.crazyit.firstboot.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

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
public class MockTest
{
	// 定义要测试的目标组件：BookService
	@Autowired
	private BookService bookService;
	// 为BookService依赖的组件定义一个Mock Bean
	// 该Mock Bean将会被注入被测试的目标组件
	@MockBean
	private BookDao bookDao;

	@Test
	public void testGetAllBooks()
	{
		// 模拟bookDao的findAll()方法的返回值
		BDDMockito.given(this.bookDao.findAll()).willReturn(
				List.of(new Book("测试1", "李刚", 89.9),
					new Book("测试2", "yeeku", 99.9)));
		List<Book> result = bookService.getAllBooks();
		Assertions.assertEquals(result.get(0).getTitle(), "测试1");
		Assertions.assertEquals(result.get(0).getAuthor(), "李刚");
		Assertions.assertEquals(result.get(1).getTitle(), "测试2");
		Assertions.assertEquals(result.get(1).getAuthor(), "yeeku");
	}
}
