package org.crazyit.app.dao;

import org.crazyit.app.domain.Author;
import org.crazyit.app.domain.AuthorId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

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

	@ParameterizedTest
	@CsvSource({"1, 李刚, 123445, 广州",
			"2, ligang, 3432433, guangzhou"})
	public void testSave(Integer id, String name,
			String password, String addr)
	{
		var author = new Author(new AuthorId(id, name, password), addr);
		authorDao.save(author);
	}

	@ParameterizedTest
	@CsvSource({"1, 李刚, 123445", "2, ligang, 3432433"})
	public void testFindById(Integer id, String name, String password)
	{
		authorDao.findById(new AuthorId(id, name, password)).ifPresent(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2})
	public void testFindByIdId(Integer id)
	{
		authorDao.findByIdId(id).ifPresent(System.out::println);
	}
}