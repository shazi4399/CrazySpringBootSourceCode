package org.crazyit.app.domain;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
public class Book
{
	private Integer id;
	private String title;
	private String cover;
	private String author;
	private Double price;

	public Book(){}

	public Book(Integer id, String title, String cover, String author, Double price)
	{
		this.id = id;
		this.title = title;
		this.cover = cover;
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

	public String getCover()
	{
		return cover;
	}

	public void setCover(String cover)
	{
		this.cover = cover;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public Double getPrice()
	{
		return price;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}
}
