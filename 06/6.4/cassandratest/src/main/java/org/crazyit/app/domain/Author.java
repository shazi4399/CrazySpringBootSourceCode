package org.crazyit.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
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
@Table("author_inf")
public class Author
{
	@Id
	private AuthorId id;
	@Column("author_addr")
	private String addr;
	public Author(){}
	public Author(AuthorId id, String addr)
	{
		this.id = id;
		this.addr = addr;
	}

	public AuthorId getId()
	{
		return id;
	}

	public void setId(AuthorId id)
	{
		this.id = id;
	}

	public String getAddr()
	{
		return addr;
	}

	public void setAddr(String addr)
	{
		this.addr = addr;
	}

	@Override
	public String toString()
	{
		return "Author{" +
				"id=" + id +
				", addr='" + addr + '\'' +
				'}';
	}
}
