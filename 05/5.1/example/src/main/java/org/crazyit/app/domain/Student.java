package org.crazyit.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
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
@Table(name = "student_inf")
public class Student
{
	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	private int age;
	private char gender;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Clazz.class)
	// 定义名为person_id的外键列，该外键列引用person_inf表的person_id列
	@JoinColumn(name = "clazz_code", referencedColumnName = "clazz_code",
			nullable = true)
	private Clazz clazz;

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
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}

	public char getGender()
	{
		return gender;
	}
	public void setGender(char gender)
	{
		this.gender = gender;
	}
	public Clazz getClazz()
	{
		return clazz;
	}
	public void setClazz(Clazz clazz)
	{
		this.clazz = clazz;
	}

	@Override
	public String toString()
	{
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address='" + address + '\'' +
				", age=" + age +
				", gender=" + gender +
				'}';
	}
}
