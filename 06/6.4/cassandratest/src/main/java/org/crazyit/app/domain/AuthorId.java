package org.crazyit.app.domain;

import jnr.ffi.annotations.In;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

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
@PrimaryKeyClass
public class AuthorId
{
	// 该列作为分区key
	@PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
	private Integer id;
	// 该列默认作为聚簇key
	@PrimaryKeyColumn
	private String name;
	// 显式指定该列作为聚簇key
	@PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
	private String password;

	public AuthorId() { }

	public AuthorId(Integer id, String name, String password)
	{
		this.id = id;
		this.name = name;
		this.password = password;
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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return "AuthorId{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
