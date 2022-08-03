package org.crazyit.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.HashIndexed;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection="book")
public class Book
{
	@HashIndexed
	@Id
	// 建议使用String类型的Id
	private String id;
	private String name;
	// 利用MongoDB的索引功能为该属性建立索引
	@Indexed
	private String description;
	private Double price;

	public Book() {}
	public Book(String name, String description, Double price)
	{
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public Double getPrice()
	{
		return price;
	}
	public void setPrice(Double price)
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return "Book{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				'}';
	}
}
