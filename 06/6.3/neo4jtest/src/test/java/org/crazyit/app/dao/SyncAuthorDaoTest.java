package org.crazyit.app.dao;

import org.crazyit.app.domain.Author;
import org.crazyit.app.domain.Book;
import org.crazyit.app.domain.Wrote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SyncAuthorDaoTest
{
	@Autowired
	private SyncAuthorDao authorDao;

	@Test
	public void testSave()
	{
		Author author = new Author("李刚", "广州");
		// 创建Book对象，并建立Book与Author的关系
		Book book = new Book("疯狂Python讲义", 118);
		book.setAuthor(author);
		// 创建Wrote关系
		Wrote wrote1 = new Wrote("2018", book);
		author.getBooks().add(wrote1);

		Book book2 = new Book("疯狂Java讲义", 129);
		book2.setAuthor(author);
		Wrote wrote2 = new Wrote("2017", book2);
		author.getBooks().add(wrote2);
		authorDao.save(author);
	}

	@ParameterizedTest
	@ValueSource(longs = {11L})
	public void testFindById(Long id)
	{
		authorDao.findById(id).ifPresent(author -> {
			System.out.println(author + "---->" + author.getBooks());
			author.getBooks().forEach(writtenBy -> System.out.println(writtenBy.getBook()));
		});
	}
}