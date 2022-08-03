package org.crazyit.app.dao;

import org.crazyit.app.domain.Student;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

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
	@CsvSource({"孙悟空, 男", "蜘蛛精, 女"})
	public void testExampleQuery1(String name, char gender)
	{
		// 创建样本对象（probe）
		var s = new Student();
		s.setName(name);
		s.setGender(gender);
		// 不使用ExampleMatcher，创建默认的Example
		studentDao.findOne(Example.of(s)).ifPresent(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"孙悟空, 男", "蜘蛛精, 女"})
	public void testExampleQuery2(String name, char gender)
	{
		// 创建matchingAll的ExampleMatcher
		ExampleMatcher matcher = ExampleMatcher.matching()
				// 忽略null属性, 该方法可以省略
				//.withIgnoreNullValues()
				.withIgnorePaths("age"); // 忽略age属性
		// 创建样本对象（probe）
		var s = new Student();
		s.setName(name);
		s.setGender(gender);
		studentDao.findOne(Example.of(s, matcher)).ifPresent(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"孙悟空, 男", "蜘蛛精, 女"})
	public void testExampleQuery3(String name, char gender)
	{
		// 创建matchingAny的ExampleMatcher
		ExampleMatcher matcher = ExampleMatcher.matchingAny()
				.withIgnorePaths("age"); // 忽略age属性
		// 创建样本对象（probe）
		var s = new Student();
		s.setName(name);
		s.setGender(gender);
		// 如果底层返回多个记录，使用findOne()方法会报异常
		studentDao.findAll(Example.of(s, matcher)).forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"孙, 男", "蜘, 女"})
	public void testExampleQuery4(String name, char gender)
	{
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("age") // 忽略age属性
				.withMatcher("name",
						// 指定匹配规则
						ExampleMatcher.GenericPropertyMatcher.of(
						ExampleMatcher.StringMatcher.STARTING));
		// 创建样本对象（probe）
		var s = new Student();
		s.setName(name);
		s.setGender(gender);
		studentDao.findOne(Example.of(s, matcher))
				.ifPresent(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"悟, 男", "精, 女"})
	public void testExampleQuery5(String name, char gender)
	{
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("age") // 忽略age属性
				.withMatcher("name",
				// 使用Lambda表达式指定匹配规则
				m -> m.contains().ignoreCase());
		// 创建样本对象（probe）
		var s = new Student();
		s.setName(name);
		s.setGender(gender);
		studentDao.findAll(Example.of(s, matcher))
				.forEach(System.out::println);
	}
}