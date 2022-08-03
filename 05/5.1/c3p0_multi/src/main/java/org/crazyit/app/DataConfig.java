package org.crazyit.app;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

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
public class DataConfig
{
	@Bean
//	@Primary // 设置该DataSource是自动装配的主候选Bean
	@ConfigurationProperties(prefix = "app.datasource.first")
	public DataSource dataSource1()
	{
		// 指定创建C3PO数据源
		return new ComboPooledDataSource();
	}

	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.second")
	public DataSourceProperties dataSourceProperties()  // ①
	{
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.second.conf")
	@Primary
	public DataSource dataSource2()
	{
		return dataSourceProperties().initializeDataSourceBuilder()
				.type(HikariDataSource.class).build();
	}
}
