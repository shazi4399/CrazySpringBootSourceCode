package org.crazyit.app.domain;

import java.util.Objects;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
public class User
{
	private Integer id;
	private String username;
	private String pass;

	// 无参数的构造器
	public User()
	{
	}
	// 初始化全部成员变量的构造器
	public User(Integer id, String username, String pass)
	{
		this.id = id;
		this.username = username;
		this.pass = pass;
	}

	// id的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	// username的setter和getter方法
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	// pass的setter和getter方法
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return this.pass;
	}

	@Override
	public String toString()
	{
		return "User[id=" + this.id + ", username="
			+ this.username + ", pass=" + this.pass + "]";
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null && obj.getClass() == User.class)
		{
			User target = (User) obj;
			return Objects.equals(this.getUsername(), target.getUsername())
				&& Objects.equals(this.getPass(), target.getPass());
		}
		return false;
	}
}