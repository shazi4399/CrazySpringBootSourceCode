package org.crazyit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

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
// 导入类加载路径下fk/crazyit.properties文件
@PropertySource("classpath:/fk/crazyit.properties")
public class App
{
	public static void main(String[] args)
	{
		// 创建Spring容器、运行Spring Boot应用
		SpringApplication.run(App.class, args);
	}
}
