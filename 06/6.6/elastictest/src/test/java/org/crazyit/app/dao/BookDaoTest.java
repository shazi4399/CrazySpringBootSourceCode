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
public class BookDaoTest
{
	@Autowired
	private BookDao bookDao;

	@ParameterizedTest
	@CsvSource({"1, 疯狂Java讲义, 最全面深入的Java图书, 129.0",
			"2, SpringBoot终极讲义, 无与伦比的SpringBoot热点图书, 119.0",
			"3, 疯狂Python, 系统易懂的Python图书，覆盖数据分析、爬虫等全部热门内容, 118.0"})
	public void testSave(Integer id, String name,
			String description, Double price)
	{
		var book = new Book(id, name, description, price);
		bookDao.save(book);
	}

	@Test
	public void testDelete()
	{
		// 删除id为3的Book对象
		bookDao.deleteById(3);
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂"})
	public void testFindByName(String name)
	{
		bookDao.findByName(name)
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
	@CsvSource({"110, 120", "120, 130"})
	public void testFindByPriceBetween(double start, double end)
	{
		bookDao.findByPriceBetween(start, end)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"全.+", "热.+"})
	public void testFindByDescriptionMatches(String descPattern)
	{
		bookDao.findByDescriptionMatches(descPattern)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"description, 全部", "description, 全*"})
	public void testFindByQuery1(String field, String term)
	{
		bookDao.findByQuery1(field, term)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"疯狂, 深*", "讲*, 热*"})
	public void testCustomQuery1(String name, String description)
	{
		bookDao.customQuery1(name, description)
				.forEach(System.out::println);
	}
}