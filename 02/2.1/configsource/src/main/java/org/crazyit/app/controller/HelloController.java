package org.crazyit.app.controller;

import org.fkit.app.Dog;
import org.fkjava.app.Bird;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.Date;

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
	// 依赖注入容器中的Dog类型的Bean
	@Autowired
	private Dog dog;
	// 依赖注入容器中的Bird类型的Bean
	@Autowired
	private Bird bird;
	// 依赖注入容器中的DateFormat类型的Bean
	@Autowired
	private DateFormat dateFormat;

	@GetMapping("/")
	public String test()
	{
		return "Hello, " +
				dog.bark() + ", " +
				bird.fly() + ", " +
				dateFormat.format(new Date());
	}
}
