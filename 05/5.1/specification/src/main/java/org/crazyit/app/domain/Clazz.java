/**
 *
 */
package org.crazyit.app.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "clazz_inf")
public class Clazz
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clazz_code")
	private Integer code;
	private String name;

	// 班级与学生是一对多的关联
	@OneToMany(fetch = FetchType.LAZY, targetEntity = Student.class, mappedBy = "clazz")
	private Set<Student> students = new HashSet<>();

	public Clazz()
	{
	}

	public Clazz(String name)
	{
		this.name = name;
	}

	public Integer getCode()
	{
		return code;
	}
	public void setCode(Integer code)
	{
		this.code = code;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Set<Student> getStudents()
	{
		return students;
	}

	public void setStudents(Set<Student> students)
	{
		this.students = students;
	}

	@Override
	public String toString()
	{
		return "Clazz{" +
				"code=" + code +
				", name='" + name + '\'' +
				'}';
	}
}