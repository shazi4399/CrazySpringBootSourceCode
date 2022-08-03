package org.crazyit.app.domain.news;

import javax.persistence.*;

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
@Table(name = "news_inf")
public class News
{
	@Id
	@Column(name = "news_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "news_title")
	private String title;
	@Column(name = "news_content")
	private String content;

	public News() {}
	public News(String title, String content)
	{
		this.title = title;
		this.content = content;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	@Override
	public String toString()
	{
		return "News{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
