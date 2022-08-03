package org.crazyit.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "user_inf")
public class User
{
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private char gender;
	private int age;
	public User(){}
	public User(String name, char gender, int age)
	{
		this.name = name;
		this.gender = gender;
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
	public char getGender()
	{
		return gender;
	}
	public void setGender(char gender)
	{
		this.gender = gender;
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
				", gender=" + gender +
				", age=" + age +
				'}';
	}
}
