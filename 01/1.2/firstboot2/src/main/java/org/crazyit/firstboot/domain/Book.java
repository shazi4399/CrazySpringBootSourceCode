package org.crazyit.firstboot.domain;

import javax.persistence.*;

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
@Entity
@Table(name = "book_inf")
public class Book
{
	@Id
	@Column(name = "book_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String author;
	private double price;

	public Book(){}

	public Book(String title, String author, double price)
	{
		this.title = title;
		this.author = author;
		this.price = price;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
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

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return "Book{" +
				"id=" + id +
				", title='" + title + '\'' +
				", author='" + author + '\'' +
				", price=" + price +
				'}';
	}
}
