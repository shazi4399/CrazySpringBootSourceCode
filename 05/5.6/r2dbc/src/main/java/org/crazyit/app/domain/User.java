package org.crazyit.app.domain;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

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
@Table("user_inf")
public class User
{
	@Id
	@Column("user_id")
	private Integer id;
	private String name;
	// 指定password属性使用setter、getter方法访问，
	// 其他属性直接通过Field访问
	@AccessType(AccessType.Type.PROPERTY)
	private  String password;
	private int age;
	public User(){}
	@PersistenceConstructor
	public User(String name, String password, int age)
	{
		this.name = name;
		this.password = password;
		this.age = age;
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
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	@Override
	public String toString()
	{
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", age=" + age +
				'}';
	}
}
