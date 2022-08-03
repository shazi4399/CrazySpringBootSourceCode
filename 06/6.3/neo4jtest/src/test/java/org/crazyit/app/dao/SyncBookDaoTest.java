package org.crazyit.app.dao;

import org.crazyit.app.domain.Author;
import org.crazyit.app.domain.Book;
import org.crazyit.app.domain.Wrote;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

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
	@Autowired
	private SyncAuthorDao authorDao;

	@Test
	public void testSave()
	{
		Book book = new Book("疯狂Android讲义", 128);
		Author author = authorDao.findByName("李刚");
		author.getBooks().add(new Wrote("2017", book));
		book.setAuthor(author);
		bookDao.save(book);
	}

	@ParameterizedTest
	@ValueSource(longs = {13L})
	public void testFindById(Long id)
	{
		bookDao.findById(id).ifPresent(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂*"})
	public void testFindByTitleLike(String titlePattern)
	{
		bookDao.findByTitleLike(titlePattern)
				.forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂.+"})
	public void testFindByTitleMatches(String regex)
	{
		bookDao.findByTitleMatches(regex)
				.forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@ValueSource(strings = {"Java", "Python"})
	public void testFindByTitleContains(String subTitle)
	{
		System.out.println(subTitle);
		bookDao.findByTitleContaining(subTitle)
				.forEach(book -> System.out
						.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "100, 110"})
	public void testFindByPriceBetween(double start, double end)
	{
		bookDao.findByPriceBetween(start, end)
				.forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@CsvSource({"疯狂Java讲义, 129"})
	public void testExampleQuery1(String title, int price)
	{
		// 创建样本对象（probe）
		var s = new Book(title, price);
		// 不使用ExampleMatcher，创建默认的Example
		bookDao.findAll(Example.of(s))
				.forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂Python讲义"})
	public void testExampleQuery2(String title)
	{
		// 创建matchingAll的ExampleMatcher
		ExampleMatcher matcher = ExampleMatcher.matching()
				// 忽略null属性, 该方法可以省略
				//.withIgnoreNullValues()
				.withIgnorePaths("price"); // 忽略price属性
		// 创建样本对象（probe）
		var s = new Book(title, 1);
		bookDao.findAll(Example.of(s, matcher))
				.forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "120, 130"})
	public void testFindByQuery1(double start, double end)
	{
		bookDao.findByQuery1(start, end)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂.+", "疯狂Python.*"})
	public void testFindByQuery2(String titlePattern)
	{
		bookDao.findByQuery2(titlePattern)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"疯狂.+, 120", "疯狂Python.*, 110"})
	public void testCustomQuery1(String regex, int startPrice)
	{
		bookDao.customQuery1(regex, startPrice)
				.forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "120, 130"})
	public void testCustomQuery2(int startPrice, int endPrice)
	{
		bookDao.customQuery2(startPrice, endPrice)
				.forEach(book -> System.out
						.println(book + "-->" + book.getAuthor()));
	}
}