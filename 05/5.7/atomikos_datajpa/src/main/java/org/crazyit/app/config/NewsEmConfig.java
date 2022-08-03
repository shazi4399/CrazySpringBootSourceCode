package org.crazyit.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

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
// 使用该注解指定自行配置JPA Repository
@EnableJpaRepositories(
		entityManagerFactoryRef = "newsEntityManager",
		// 使用默认的的事务管理器，
		// 当全局JTA事务管理器的id为transactionManager时，该属性可省略
		transactionManagerRef = "transactionManager",
		basePackages = {"org.crazyit.app.dao.news"}) // 设置DAO组件所在位置
public class NewsEmConfig
{
	@Autowired
	@Qualifier("secondDataSource")
	private DataSource secondDataSource;

	@Bean(name = "newsEntityManager")
	@DependsOn("transactionManager")
	public LocalContainerEntityManagerFactoryBean newsEntityManagerFactory(
			EntityManagerFactoryBuilder builder)
	{
		return builder.dataSource(secondDataSource)
				// 设置实体类所在的包
				.packages("org.crazyit.app.domain.news")
				.persistenceUnit("newsPersistenceUnit")
				.build();
	}
}