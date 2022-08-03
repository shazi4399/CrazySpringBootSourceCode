package org.crazyit.app;

import org.crazyit.app.domain.Book;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
@Configuration
public class MyConfig
{
	@Bean
	// @ConfigurationProperties注解会驱动Spring自动调用该Bean的setter方法
	@ConfigurationProperties("fkjava.book")
	public Book book()
	{
		return new Book();
	}
}
