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
@Node(labels = {"Book", "Item"}, primaryLabel = "Book")
public class Book
{
	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private int price;
	@Relationship(type = "WRITTEN_BY", direction = Relationship.Direction.OUTGOING)
	private Author author;

	public Book(){}
	public Book(String title, int price)
	{
		this.id = null;
		this.title = title;
		this.price = price;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getPrice()
	{
		return price;
	}
	public void setPrice(int price)
	{
		this.price = price;
	}
	public Author getAuthor()
	{
		return author;
	}
	public void setAuthor(Author author)
	{
		this.author = author;
	}
	public Book withId(Long id)
	{
		if (this.id.equals(id))
		{
			return this;
		}
		else
		{
			Book newObject = new Book(this.title, this.price);
			newObject.id = id;
			return newObject;
		}
	}

	@Override
	public String toString()
	{
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", price=" + price +
				'}';
	}
}
