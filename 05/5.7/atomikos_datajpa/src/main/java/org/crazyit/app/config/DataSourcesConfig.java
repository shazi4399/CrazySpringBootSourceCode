package org.crazyit.app.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.postgresql.xa.PGXADataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import javax.sql.XADataSource;

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
public class DataSourcesConfig
{
	@Bean
	// 通过@ConfigurationProperties注解控制该Bean调用对应的setter方法
	@ConfigurationProperties(prefix = "spring.datasource.first")
	public XADataSource initFirstDatasource()
	{
		return new MysqlXADataSource();
	}

	@Bean(name = "firstDataSource")
	@Primary
	@DependsOn("initFirstDatasource")
	public DataSource firstDataSource()
	{
		System.out.println("创建第1个数据源");
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(initFirstDatasource());
		xaDataSource.setUniqueResourceName("mysqlDataSource");
		xaDataSource.setPoolSize(30);
		return xaDataSource;
	}

	@Bean
	// 通过@ConfigurationProperties注解控制该Bean调用对应的setter方法
	@ConfigurationProperties(prefix = "spring.datasource.second")
	public XADataSource initSecondDatasource()
	{
		return new PGXADataSource();
	}

	@Bean(name = "secondDataSource")
	@DependsOn("initSecondDatasource")
	public DataSource secondDataSource()
	{
		System.out.println("创建第2个数据源");
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(initSecondDatasource());
		xaDataSource.setUniqueResourceName("pgsqlDataSource");
		xaDataSource.setPoolSize(30);
		return xaDataSource;
	}
}