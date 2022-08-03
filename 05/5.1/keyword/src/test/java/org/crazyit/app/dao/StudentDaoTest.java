package org.crazyit.app.dao;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.transaction.Transactional;

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
	@ValueSource(strings = {"花果山水帘洞", "积雷山摩云洞"})
	public void testFindByAddress(String addr)
	{
		studentDao.findByAddress(addr).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(ints = {500, 550, 700})
	public void testFindByAgeGreaterThan(int start)
	{
		studentDao.findByAgeGreaterThan(start).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"孙", "白", "沙"})
	public void testFindByNameStartsWith(String namePrefix)
	{
		studentDao.findByNameStartsWith(namePrefix).forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"500, 600, 花果山水帘洞", "18, 24, 积雷山摩云洞"})
	public void testFindByAgeBetweenAndAddress(int start, int end, String addr)
	{
		studentDao.findByAgeBetweenAndAddress(start, end, addr).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%就业班", "%训练营"})
	public void testFindByClazzNameLike(String clazzPattern)
	{
		studentDao.findByClazzNameLike(clazzPattern).forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"花果山水帘洞", "积雷山摩云洞"})
	@Transactional
	public void testDeleteByAddress(String addr)
	{
		System.out.println("删除的记录数：" +  studentDao.deleteByAddress(addr));
	}

	@ParameterizedTest
	@CsvSource({"1, 3", "2, 2", "3, 2"})
	public void testFindByClazzNameLike(int page, int size)
	{
		PageRequest pagable = PageRequest.of(page - 1, size);
		studentDao.findByClazzNameLike("%Java%", pagable).forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"女, 20, 25, %基础班", "女, 18, 20, %训练营"})
	public void testFindByGenderAndAgeBetweenOrClazzNameLike(char gender,
			int start, int end, String clazzPattern)
	{
		studentDao.findByGenderAndAgeBetweenOrClazzNameLike(gender, start,
				end, clazzPattern).forEach(System.out::println);
	}
}
