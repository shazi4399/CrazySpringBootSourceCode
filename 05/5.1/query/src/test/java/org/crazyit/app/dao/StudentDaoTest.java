package org.crazyit.app.dao;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;

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
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testFindByClazzNameJPQL(String clazzName)
	{
		studentDao.findByClazzNameJPQL(clazzName)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testFindByClazzNameNative(String clazzName)
	{
		studentDao.findByClazzNameNative(clazzName)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testFindNameAndGenderByClazzNameJPQL1(String clazzName)
	{
		studentDao.findNameAndGenderByClazzNameJPQL1(clazzName).forEach(
				arr -> System.out.println(Arrays.toString(arr)));
	}

	@ParameterizedTest
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testFindNameAndGenderByClazzNameJPQL2(String clazzName)
	{
		studentDao.findNameAndGenderByClazzNameJPQL2(clazzName)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testFindNameAndGenderByClazzNameJPQL3(String clazzName)
	{
		studentDao.findNameAndGenderByClazzNameJPQL3(clazzName)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testFindNameAndGenderByClazzNameJPQL4(String clazzName)
	{
		studentDao.findNameAndGenderByClazzNameJPQL4(clazzName)
				.forEach(map -> map.forEach((key, val)
						-> System.out.println(key + "-->" + val)));
	}

	@ParameterizedTest
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testFindNameAndGenderByClazzNameJPQL5(String clazzName)
	{
		studentDao.findNameAndGenderByClazzNameJPQL5(clazzName).forEach(
				arr -> System.out.println(Arrays.toString(arr)));
	}

	@Transactional
	@ParameterizedTest
	@CsvSource({"Spring企业开发, 1", "Spring Boot提高班, 2"})
//	@Rollback(false)
	public void testUpdateClazzNameById(String clazzName, Integer id)
	{
		System.out.println(studentDao.updateClazzNameById(clazzName, id));
	}

	@ParameterizedTest
	@Transactional
	@ValueSource(ints = {3, 4})
	public void testDeleteClazzById(Integer id)
	{
		System.out.println(studentDao.deleteClazzById(id));
	}

	@ParameterizedTest
	@CsvSource({"%提高班, 女", "%基础班, 女"})
	public void testNamedJpql(String clazzNamePattern, char gender)
	{
		studentDao.namedJpql(clazzNamePattern, gender)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%训练营", "%基础班"})
	public void testNamedSql(String clazzNamePattern)
	{
		studentDao.namedSql(clazzNamePattern).forEach(System.out::println);
	}
}