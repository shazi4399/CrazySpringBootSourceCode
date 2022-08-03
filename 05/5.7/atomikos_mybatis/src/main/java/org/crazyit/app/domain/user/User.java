package org.crazyit.app.domain.user;

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
public class User
{
	private Integer id;
	private String name;
	private String password;
	private int age;
	public User() {}
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
