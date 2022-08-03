package org.crazyit.app.config;

import org.crazyit.app.domain.Book;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@ConfigurationProperties("crazyit")
public class CrazyitProperties
{
	private final List<Book> list = new ArrayList<>();
	private final Map<String, Book> map = new HashMap<>();
	public List<Book> getList()
	{
		return this.list;
	}
	public Map<String, Book> getMap()
	{
		return this.map;
	}
}