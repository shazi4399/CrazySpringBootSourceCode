package org.crazyit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
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
		var application = new SpringApplication(App.class);
		// 设置不再启动Web应用
		application.setWebApplicationType(WebApplicationType.NONE);
		// 或显式设置Spring Boot应用所使用的Spring容器
//		application.setApplicationContextClass(AnnotationConfigApplicationContext.class);
		application.run(args);
	}
}
