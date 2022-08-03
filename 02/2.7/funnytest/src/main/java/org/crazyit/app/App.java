package org.crazyit.app;

import org.crazyit.funny.io.WriterTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
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
@SpringBootApplication
public class App
{
	public static void main(String[] args) throws IOException, SQLException
	{
		// 创建Spring容器、运行Spring Boot应用
		var ctx = SpringApplication.run(App.class, args);
		// 获取自动配置的WriterTemplate
		WriterTemplate writerTemplate = ctx.getBean(WriterTemplate.class);
		writerTemplate.write("自动配置其实很简单");
	}
}
