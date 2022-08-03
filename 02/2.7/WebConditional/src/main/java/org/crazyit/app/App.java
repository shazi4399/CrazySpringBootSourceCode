package org.crazyit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
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
		var app = new SpringApplication(App.class);
		// 设置Web应用的类型，如果不设置则使用默认的类型：
		// 如果有Sping Web依赖，自动是基于Servlet的Web应用
		// 如果有Sping WebFlux依赖，自动是反应式Web应用
		app.setWebApplicationType(WebApplicationType.REACTIVE);  // ①
		// 创建Spring容器、运行Spring Boot应用
		var ctx = app.run(args);
		System.out.println(ctx.getBean("dateFormat"));
	}
}
