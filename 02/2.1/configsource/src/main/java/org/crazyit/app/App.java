package org.crazyit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
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
// 额外指定扫描org.crazyit.app和org.fkit.app包及其子包下所有配置类和Bean组件
@SpringBootApplication(scanBasePackages = {"org.crazyit.app", "org.fkit.app"})
// 加载类加载路径下beans.xml文件作为配置文件
@ImportResource("classpath:beans.xml")
// 加载cn.fkjava.app包下的MyConfig文件作为配置类
@Import(cn.fkjava.app.MyConfig.class)
public class App
{
	public static void main(String[] args)
	{
		// 创建Spring容器、运行Spring Boot应用
		SpringApplication.run(App.class, args);
	}
}
