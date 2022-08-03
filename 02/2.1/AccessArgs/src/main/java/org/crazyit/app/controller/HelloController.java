package org.crazyit.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
	// 依赖注入容器中的ApplicationArguments Bean
	@Autowired
	private ApplicationArguments args;
	@GetMapping("/")
	public void test()
	{
		System.out.println("访问应用的运行参数");
		System.out.println("getSourceArgs:" + Arrays.toString(args.getSourceArgs()));
		System.out.println("getOptionValues:" + args.getOptionValues("book.name"));
		System.out.println("getNonOptionArgs:" + args.getNonOptionArgs());
	}
}
