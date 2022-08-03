package org.crazyit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
public class App
{
	public static void main(String[] args)
	{
		// 设置spring.application.json系统属性
//		System.setProperty("spring.application.json",
//				"{\"fkjava\":{\"name\":\"疯狂软件\", \"age\":20, " +
//				"\"servers\":[\"fkjava.org\", \"crazyit.org\"]}}");
		SpringApplication.run(App.class, args);
	}
}
