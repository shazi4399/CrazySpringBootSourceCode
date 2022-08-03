package org.crazyit.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@Value("${app.java.version}")
	private String javaVersion;
	@Value("${app.name}")
	private String appName;
	@Value("${app.version}")
	private String appVersion;
	@GetMapping
	public Map<String, String> hello()
	{
		return Map.of("javaVersion", javaVersion,
				"appName", appName,
				"appVersion", appVersion);
	}
}
