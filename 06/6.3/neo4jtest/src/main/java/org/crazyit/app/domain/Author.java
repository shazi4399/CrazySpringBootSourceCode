package org.crazyit.app.domain;

import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

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
@Node(labels = {"Author", "Person", "Dad"}, primaryLabel = "Author")
public class Author
{
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@Property("author_addr")
	private String addr;
	@Relationship(type = "WROTE", direction = Relationship.Direction.OUTGOING)
	private List<Wrote> books = new ArrayList<>();

	public Author(){}
	public Author(String name, String addr)
	{
		this.name = name;
		this.addr = addr;
	}
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
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
	public String getAddr()
	{
		return addr;
	}
	public void setAddr(String addr)
	{
		this.addr = addr;
	}
	public List<Wrote> getBooks()
	{
		return books;
	}
	public void setBooks(List<Wrote> books)
	{
		this.books = books;
	}
	public Author withId(Long id)
	{
		if (this.id.equals(id))
		{
			return this;
		}
		else
		{
			Author newObject = new Author(this.name, this.addr);
			newObject.id = id;
			return newObject;
		}
	}

	@Override
	public String toString()
	{
		return "Author{" +
				"id=" + id +
				", name='" + name + '\'' +
				", addr='" + addr + '\'' +
				'}';
	}
}
