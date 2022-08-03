package org.crazyit.app.dao;

import org.crazyit.app.domain.UserInf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

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
public class UserInfDaoTest
{
	@Autowired
	private UserInfDao userInfDao;

	@ParameterizedTest
	@CsvSource({"fkjava, fkjava123, 23", "crazyit, crazyit23, 24"})
	public void testSave(String name, String password, int age)
	{
		var user = new UserInf(name, password, age);
		user = userInfDao.save(user);
		System.out.println("保存后的user的id为：" + user.getUserId());
	}
	@ParameterizedTest
	@CsvSource({"1, foo", "2, bar"})
	public void testUpdate(Integer id, String name)
	{
		var user = userInfDao.findById(id).get();
		user.setName(name);
		userInfDao.save(user);
		System.out.println("更新后的user的id为：" + user.getUserId());
	}
	@ParameterizedTest
	@ValueSource(strings = {"白鼠精", "蜘蛛精"})
	public void testFindByName(String name)
	{
		userInfDao.findByName(name).forEach(System.out::println);
	}
	@ParameterizedTest
	@ValueSource(strings = {"zhu%", "bai%"})
	public void testFindByPasswordLike(String passPattern)
	{
		userInfDao.findByPasswordLike(passPattern)
				.forEach(System.out::println);
	}
	@ParameterizedTest
	@CsvSource({"18, 20", "22, 25"})
	public void testFindByAgeBetween(int start, int end)
	{
		userInfDao.findByAgeBetween(start, end)
				.forEach(System.out::println);
	}
	@ParameterizedTest
	@CsvSource({"八, zhu", "蜘蛛, 123"})
	public void testFindByNameContainsAndPasswordStartsWith(
			String subName, String passPrefix)
	{
		userInfDao.findByNameContainsAndPasswordStartsWith(subName,
				passPrefix).forEach(System.out::println);
	}
	@ParameterizedTest
	@CsvSource({"玉面%, 20", "蜘蛛%, 22"})
	public void testFindBySql(String namePattern, int minAge)
	{
		userInfDao.findBySql(namePattern, minAge)
				.forEach(System.out::println);
	}
	@ParameterizedTest
	@CsvSource({"武松, 1", "金莲, 2"})
	public void testUpdateNameById(String name, Integer id)
	{
		System.out.println(userInfDao.updateNameById(name, id));
	}
	@ParameterizedTest
	@CsvSource({"%123%, 18, 20", "%yumian%, 24, 30"})
	public void testCustomQuery(String passPattern,
			int startAge, int endAge) throws SQLException
	{
		userInfDao.customQuery(passPattern, startAge, endAge)
				.forEach(System.out::println);
	}
	@ParameterizedTest
	@CsvSource({"18, 20", "24, 30"})
	public void testJdbcTemplateQuery(int startAge, int endAge)
	{
		userInfDao.jdbcTemplateQuery(startAge, endAge)
				.forEach(System.out::println);
	}
}

