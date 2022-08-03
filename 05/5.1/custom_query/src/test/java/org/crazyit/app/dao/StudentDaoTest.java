package org.crazyit.app.dao;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
public class StudentDaoTest
{
	@Autowired
	private StudentDao studentDao;

	@ParameterizedTest
	@ValueSource(strings = {"孙%", "白%"})
	public void testCustomQuery(String namePattern)
	{
		studentDao.customQuery(namePattern).forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"18, 20", "20, 25"})
	public void testCustomSqlQuery(int startAge, int endAge)
	{
		studentDao.customSqlQuery(startAge, endAge)
				.forEach(System.out::println);
	}
}