package org.crazyit.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.concurrent.TimeUnit;

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
@RedisHash("book")
public class Book
{
	// 标识属性，可用于查找
	@Id
	private Integer id;
	// 带@Indexed注解的属性被称为二级索引，可用于被查询
	@Indexed
	private String name;
	@Indexed
	private String description;
	private Double price;
	// 定义它的超时时长
	@TimeToLive(unit = TimeUnit.HOURS)
	Long timeout;

	public Book() {}
	public Book(String name, String description, Double price)
	{
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
	public Long getTimeout()
	{
		return timeout;
	}
	public void setTimeout(Long timeout)
	{
		this.timeout = timeout;
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
