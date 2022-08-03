package org.crazyit.app.domain;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
public class Book
{
	@Length(min = 6, max = 30, message = "书名长度必须在6～30个字符之间")
	private String name;
	private MultipartFile cover;
	private String targetName;

	// 无参数的构造器
	public Book()
	{
	}

	// 初始化全部成员变量的构造器
	public Book(String name, MultipartFile cover, String targetName)
	{
		this.name = name;
		this.cover = cover;
		this.targetName = targetName;
	}

	// name的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	// cover的setter和getter方法
	public void setCover(MultipartFile cover)
	{
		this.cover = cover;
	}

	public MultipartFile getCover()
	{
		return this.cover;
	}

	// targetName的setter和getter方法
	public void setTargetName(String targetName)
	{
		this.targetName = targetName;
	}

	public String getTargetName()
	{
		return this.targetName;
	}
}