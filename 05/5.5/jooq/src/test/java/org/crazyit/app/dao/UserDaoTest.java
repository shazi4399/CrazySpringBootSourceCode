package org.crazyit.app.dao;

import org.crazyit.generated.tables.records.UserInfRecord;
import org.jooq.Record2;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserDaoTest
{
	@Autowired
	private UserDao userDao;

	@ParameterizedTest
	@CsvSource({"fkjava, fkjava123, 23", "crazyit, crazyit23, 24"})
	public void testSave(String name, String password, int age)
	{
		var user = new UserInfRecord(null, name, password, age);
		System.out.println("插入的记录条数：" + userDao.save(user));
	}

	@ParameterizedTest
	@CsvSource({"foo, foo123, 1", "bar, bar123, 2"})
	public void testUpdateById(String name, String password, Integer id)
	{
		System.out.println("更新的记录条数：" +
				userDao.updateById(name, password, id));
	}

	@ParameterizedTest
	@CsvSource({"猪八戒, zhu123", "白鼠精, bai1234"})
	public void testFindByNameAndPassword(String name, String password)
	{
		userDao.findByNameAndPassword(name, password)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"18, 20", "22, 25"})
	public void testFindByAgeBetween(int startAge, int endAge)
	{
		userDao.findByAgeBetween(startAge, endAge)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(ints = {500, 600})
	public void testFindNameByAgeGreatThan(int startAge)
	{
		userDao.findNameByAgeGreatThan(startAge)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(ints = {20, 22})
	public void testFindNamePasswordByAgeLessThan(int endAge)
	{
		userDao.findNamePasswordByAgeLessThan(endAge)
				.forEach(System.out::println);
	}
}
