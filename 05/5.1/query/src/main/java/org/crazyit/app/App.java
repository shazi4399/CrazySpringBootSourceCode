package org.crazyit.app;

import org.crazyit.app.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

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
		// 设置以非Web方式运行Spring Boot项目
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}
}