package org.crazyit.app;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.Compression;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.unit.DataSize;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
	public ConfigurableServletWebServerFactory webServerFactory()
	{
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		// 设置端口
		factory.setPort(8181);
		Session session = new Session();
		// 设置服务器session的超时时长为10分钟
		session.setTimeout(Duration.ofMinutes(10));
		factory.setSession(session);
		// 设置404的错误页面
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
		// 设置该服务器的context Path
		factory.setContextPath("/newtest");
		Compression compression = new Compression();
		compression.setMinResponseSize(DataSize.ofBytes(1024));
		// 设置启用HTTP响应压缩
		factory.setCompression(compression);
		return factory;
	}
}
