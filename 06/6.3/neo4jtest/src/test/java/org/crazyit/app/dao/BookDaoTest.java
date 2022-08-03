package org.crazyit.app.dao;

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
public class BookDaoTest
{
	@Autowired
	private BookDao bookDao;
	@Autowired
	private AuthorDao authorDao;

	@Test
	public void testSave()
	{
		// 先查询Book在数据库中对应的节点
		authorDao.findByName("李刚").blockOptional()
				.ifPresent(author -> {
					var book = new Book("疯狂Android讲义", 128);
					// 建立Author到Book的关系（由Wrote对象代表）
					author.getBooks().add(new Wrote("2017", book));
					// 建立Book到Author的关系
					book.setAuthor(author);
					bookDao.save(book).block();
				});
	}

	@ParameterizedTest
	@ValueSource(longs = {2L})
	public void testFindById(Long id)
	{
		bookDao.findById(id).blockOptional()
				.ifPresent(book -> System.out.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂*"})
	public void testFindByTitleLike(String titlePattern)
	{
		bookDao.findByTitleLike(titlePattern)
				// 调用toIterable()方法以阻塞式方式完成查询
				.toIterable().forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂.+"})
	public void testFindByTitleMatches(String regex)
	{
		bookDao.findByTitleMatches(regex)
				.toIterable().forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@ValueSource(strings = {"Java", "Python"})
	public void testFindByTitleContains(String subTitle)
	{
		bookDao.findByTitleContains(subTitle)
				.toIterable().forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "100, 110"})
	public void testFindByPriceBetween(double start, double end)
	{
		bookDao.findByPriceBetween(start, end)
				.toIterable().forEach(book -> System.out
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
				.toIterable().forEach(book -> System.out
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
				.toIterable().forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "120, 130"})
	public void testFindByQuery1(double start, double end)
	{
		bookDao.findByQuery1(start, end)
				.toIterable().forEach(System.out::println);
	}

	@ParameterizedTest
	@ValueSource(strings = {"疯狂.+", "疯狂Python.*"})
	public void testFindByQuery2(String titlePattern)
	{
		bookDao.findByQuery2(titlePattern)
				.toIterable().forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"疯狂.+, 120", "疯狂Python.*, 110"})
	public void testCustomQuery1(String regex, int startPrice)
	{
		bookDao.customQuery1(regex, startPrice)
				.toIterable().forEach(System.out::println);
	}

	@ParameterizedTest
	@CsvSource({"110, 120", "120, 130"})
	public void testCustomQuery2(int startPrice, int endPrice)
	{
		bookDao.customQuery2(startPrice, endPrice)
				.toIterable().forEach(book -> System.out
				.println(book + "-->" + book.getAuthor()));
	}
}