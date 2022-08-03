package org.crazyit.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
@MapperScan(basePackages = "org.crazyit.app.dao.user",
		sqlSessionFactoryRef = "sqlSessionFactory")
public class UserSqlSessionFactoryConfig
{
	@Autowired // 默认注入有@Primary修饰的DataSource Bean
	private DataSource firstDataSource;

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception
	{
		var factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(firstDataSource);
		// 如有需要，可调用factoryBean的setMapperLocations来设置XML Mapper的路径
		return factoryBean.getObject();
	}
}
