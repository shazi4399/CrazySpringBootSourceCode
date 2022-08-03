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
	@Value("${fkjava.name}")
	private String name;
	@Value("${fkjava.age}")
	private String age;
	@Value("${fkjava.servers[0]}")
	private String server1;
	@Value("${fkjava.servers[1]}")
	private String server2;
	@GetMapping
	public Map<String, String> hello()
	{
		return Map.of("名称", name, "年龄", age,
				"服务器1", server1, "服务器2", server2);
	}
}
