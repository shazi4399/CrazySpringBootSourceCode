package org.crazyit.app.dao;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.crazyit.app.domain.User;

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
public class UserDaoTest
{
	@Autowired
	private UserDao userDao;

	@ParameterizedTest
	@CsvSource({"fkjava, fkjava123, 23", "crazyit, crazyit23, 24"})
	public void testSave(String name, String password, int age)
	{
		var user = new User(name, password, age);
		System.out.println("受影响的记录条数：" + userDao.save(user));
	}

	@ParameterizedTest
	@CsvSource({"1, foo", "2, bar"})
	public void testUpdate(Integer id, String name)
	{
		var user = userDao.get(id);
		user.setName(name);
		System.out.println("受影响的记录条数：" + userDao.update(user));
	}

	@ParameterizedTest
	@ValueSource(ints = {3, 4})
	public void testDelete(Integer id)
	{
		System.out.println("受影响的记录条数：" + userDao.delete(id));
	}

	@ParameterizedTest
	@CsvSource({"18, 20", "22, 25"})
	public void testFindNameByAgeBetween(int startAge, int endAge)
	{
		userDao.findNameByAgeBetween(startAge, endAge).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"玉面%", "白%"})
	public void testFindByNameLike1(String name)
	{
		userDao.findByNameLike1(name)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"玉面%", "白%"})
	public void testFindByNameLike2(String name)
	{
		userDao.findByNameLike2(name)
				.forEach(System.out::println);
	}
}
