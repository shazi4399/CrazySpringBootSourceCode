package org.crazyit.app;

import org.crazyit.app.web.CrazyitFilter;
import org.crazyit.app.web.CrazyitListener;
import org.crazyit.app.web.FirstServlet;
import org.crazyit.app.web.SecondServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
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
	@Bean
	public ServletRegistrationBean<FirstServlet> createServlet1()
	{
		FirstServlet firstServlet = new FirstServlet();
		// 注册Servlet
		ServletRegistrationBean<FirstServlet> registrationBean =
				new ServletRegistrationBean<>(firstServlet, "/first");
		return registrationBean;
	}

	@Bean
	public ServletRegistrationBean<SecondServlet> createServlet2()
	{
		SecondServlet secondServlet = new SecondServlet();
		// 注册Servlet
		ServletRegistrationBean<SecondServlet> registrationBean =
				new ServletRegistrationBean<>(secondServlet, "/second");
		return registrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<CrazyitListener> createListener()
	{
		CrazyitListener listener = new CrazyitListener();
		// 注册Listener
		ServletListenerRegistrationBean<CrazyitListener> registrationBean =
				new ServletListenerRegistrationBean<>(listener);
		return registrationBean;
	}
	@Bean
	public FilterRegistrationBean<CrazyitFilter> createFilter()
	{
		CrazyitFilter filter = new CrazyitFilter();
		// 注册Filter
		FilterRegistrationBean<CrazyitFilter> registrationBean =
				new FilterRegistrationBean<>(filter);
		return registrationBean;
	}
}
