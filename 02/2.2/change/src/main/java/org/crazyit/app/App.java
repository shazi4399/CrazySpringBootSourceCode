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
	static {
		// 设置配置文件的文件名
		System.setProperty("spring.config.name", "application, fk");
		// 设置配置文件的加载路径
		System.setProperty("spring.config.location",
				"classpath:/, optional:classpath:/fk/");
		// 设置额外的加载路径
//		System.setProperty("spring.config.additional-location",
//				"optional:classpath:/fk/");
	}
	public static void main(String[] args)
	{
		// 创建Spring容器、运行Spring Boot应用
		SpringApplication.run(App.class, args);
	}
}
