package org.crazyit.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class HelloController
{
	// 使用@Value注解访问配置属性
	@Value("${app.description}")
	private String appDescription;
	@Value("${book.description}")
	private String bookDescription;
	@GetMapping
	public Map<String, String> hello()
	{
		return Map.of("应用描述:", appDescription,
				"图书描述: ", bookDescription);
	}
}
