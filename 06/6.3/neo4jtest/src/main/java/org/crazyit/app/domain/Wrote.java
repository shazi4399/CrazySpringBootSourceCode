package org.crazyit.app.domain;

import org.springframework.data.neo4j.core.schema.*;

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
@RelationshipProperties
public class Wrote
{
	@TargetNode
	private Book book;
	private String year;

	public Wrote() {}
	public Wrote(String year, Book book)
	{
		this.year = year;
		this.book = book;
	}

	public Book getBook()
	{
		return book;
	}
	public void setBook(Book book)
	{
		this.book = book;
	}
	public String getYear()
	{
		return year;
	}
	public void setYear(String year)
	{
		this.year = year;
	}

	@Override
	public String toString()
	{
		return "Wrote{" +
				"year='" + year + '\'' +
				'}';
	}
}
