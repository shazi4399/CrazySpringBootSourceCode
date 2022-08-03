package org.crazyit.app.controller;

import org.crazyit.app.config.CrazyitProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
	private final CrazyitProperties crazyitProperties;
	// 依赖注入CrazyitProperties属性处理Bean
	@Autowired
	public HelloController(CrazyitProperties crazyitProperties)
	{
		this.crazyitProperties = crazyitProperties;
	}

	@GetMapping
	public Map<String, Object> hello()
	{
		System.out.println(crazyitProperties.getMaxSize().toKilobytes());
		return Map.of("props", crazyitProperties,
				"maxSize", crazyitProperties.getMaxSize().toKilobytes());
	}
}
