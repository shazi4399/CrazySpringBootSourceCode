package org.crazyit.app.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

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
public class Config
{
	@Configuration(proxyBeanMethods = false)
	@Profile("mysql")
	static class MySQLInitializationConfiguration
	{
		@Autowired
		void initializeDatabase(ConnectionFactory connectionFactory)
		{
			var resourceLoader = new DefaultResourceLoader();
			// 加载初始化MySQL数据库的SQL脚本
			var scripts = new Resource[]{
					resourceLoader.getResource("classpath:schema-mysql.sql"),
					resourceLoader.getResource("classpath:data-mysql.sql")};
			// 调用block()方法以同步方式执行SQL脚本
			new ResourceDatabasePopulator(scripts)
					.populate(connectionFactory).block();
		}
	}

	@Configuration(proxyBeanMethods = false)
	@Profile("postgre")
	static class PostgreSQLInitializationConfiguration
	{
		@Autowired
		void initializeDatabase(ConnectionFactory connectionFactory)
		{
			var resourceLoader = new DefaultResourceLoader();
			// 加载初始化PostgreSQL数据库的SQL脚本
			var scripts = new Resource[]{
					resourceLoader.getResource("classpath:schema-postgre.sql"),
					resourceLoader.getResource("classpath:data-postgre.sql")};
			// 调用block()方法以同步方式执行SQL脚本
			new ResourceDatabasePopulator(scripts)
					.populate(connectionFactory).block();
		}
	}
}