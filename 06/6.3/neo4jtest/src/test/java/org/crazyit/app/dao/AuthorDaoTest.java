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
public class AuthorDaoTest
{
	@Autowired
	private AuthorDao authorDao;

	@Test
	public void testSave()
	{
		Author author = new Author("李刚", "广州");
		// 创建Book对象，并建立Book到Author的关系
		Book book = new Book("疯狂Python讲义", 118);
		book.setAuthor(author);
		// 创建Wrote关系
		Wrote wrote1 = new Wrote("2018", book);
		// 建立Author到Book的关系（由Wrote对象代表）
		author.getBooks().add(wrote1);
		// 创建第2个Book对象，并奖励它与Author的关系
		Book book2 = new Book("疯狂Java讲义", 129);
		book2.setAuthor(author);
		// 创建第2个Wrote关系
		Wrote wrote2 = new Wrote("2017", book2);
		// 建立Author到Book的关系（由Wrote对象代表）
		author.getBooks().add(wrote2);
		// 保存Author对象
		authorDao.save(author).block();
	}

	@ParameterizedTest
	@ValueSource(longs = {16L})
	public void testFindById(Long id)
	{
		authorDao.findById(id).blockOptional()
				.ifPresent(author -> {
					// 获取该Author关联的关系
					System.out.println(author + "-->" + author.getBooks());
					// 通过Author关联的关系获取关联的图书
					author.getBooks().forEach(writtenBy ->
							System.out.println(writtenBy.getBook()));
				});
	}
}