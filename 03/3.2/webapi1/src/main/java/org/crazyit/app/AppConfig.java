package org.crazyit.app;

import org.crazyit.app.web.CrazyitFilter;
import org.crazyit.app.web.CrazyitListener;
import org.crazyit.app.web.FirstServlet;
import org.crazyit.app.web.SecondServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

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
@Configuration
public class AppConfig
{
	@Bean("first")
	public HttpServlet createServlet1()
	{
		FirstServlet firstServlet = new FirstServlet();
		return firstServlet;
	}

	@Bean("second")
	public HttpServlet createServlet2()
	{
		SecondServlet secondServlet = new SecondServlet();
		return secondServlet;
	}

	@Bean
	public ServletContextListener createListener()
	{
		CrazyitListener listener = new CrazyitListener();
		return listener;
	}
	@Bean
	public Filter createFilter()
	{
		CrazyitFilter filter = new CrazyitFilter();
		return filter;
	}
}
