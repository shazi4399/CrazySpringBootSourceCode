package org.crazyit.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Properties;

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
	@Value("${fkjava.server.name}")
	private String serverName;
	@Value("${fkjava.server.port}")
	private String serverPort;
	@Value("${fkjava.age}")
	private String age;
	@GetMapping
	public String hello()
	{
		return "名称: " + serverName + ", 端口: " + serverPort
				+ ", 年龄: " + age;
	}
}
