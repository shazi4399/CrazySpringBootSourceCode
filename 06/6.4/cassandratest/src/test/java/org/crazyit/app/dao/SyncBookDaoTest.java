package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.junit.jupiter.api.Test;
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
public class SyncBookDaoTest
{
	@Autowired
	private SyncBookDao bookDao;

	@ParameterizedTest
	@CsvSource({"1, 疯狂Java讲义, 最全面深入的Java图书, 129.0",
			"2, SpringBoot终极讲义, 无与伦比的SpringBoot图书, 119.0",
			"3, 疯狂Python, 系统易懂的Python图书，覆盖数据分析、爬虫等热门内容, 118.0"})
	public void testSave(Integer id, String name,
			String description, Double price)
	{
		var book = new Book(id, name, description, price);
		bookDao.save(book);
	}

	@Test
	public void testUpdate()
	{
		// 更新id为3的Book对象
		bookDao.findById(3)
				.ifPresent(book ->
				{
					book.setName("疯狂Python讲义");
					bookDao.save(book);
				});
	}

	@Test
	public void testDelete()
	{
		// 删除id为3的Book对象
		bookDao.deleteById(3);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%疯狂%"})
	public void testFindByNameLike(String namePattern)
	{
		bookDao.findByNameLike(namePattern)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"1, 2", "2, 3"})
	public void testFindByIdIn(Integer id1, Integer id2)
	{
		bookDao.findByIdIn(List.of(id1, id2))
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(doubles = {119, 129})
	public void testFindByPrice(double price)
	{
		bookDao.findByPrice(price)
				.ifPresent(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "120, 130"})
	public void testFindByQuery1(double start, double end)
	{
		bookDao.findByQuery1(start, end)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%Java%", "%Boot%"})
	public void testFindByQuery2(String namePattern)
	{
		bookDao.findByQuery2(namePattern)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"%疯狂%", "%Boot%"})
	public void testCustomQuery1(String namePattern)
	{
		bookDao.customQuery1(namePattern)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "120, 130"})
	public void testCustomQuery2(double startPrice, double endPrice)
	{
		bookDao.customQuery2(startPrice, endPrice)
				.forEach(System.out::println);
	}
}