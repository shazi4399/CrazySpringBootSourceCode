package org.crazyit.app.domain;

import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.SASI;
import org.springframework.data.cassandra.core.mapping.Table;

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
@Table("book_inf")
public class Book
{
	@PrimaryKey
	private Integer id;
	// 使用SASI索引，从而允许使用LIKE查询
	@SASI(indexMode = SASI.IndexMode.CONTAINS)
	@SASI.StandardAnalyzed("zh")
	private String name;
	private String description;
	// 指定对price映射的列创建索引
	@Indexed
	private Double price;

	public Book() {}

	public Book(Integer id, @Indexed String name,
			String description, @Indexed Double price)
	{
		this.id = id;
		this.name = name;
		this.description = description;
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
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				'}';
	}
}
