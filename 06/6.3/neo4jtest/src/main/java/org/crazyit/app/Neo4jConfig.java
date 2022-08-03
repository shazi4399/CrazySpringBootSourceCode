package org.crazyit.app;

import org.neo4j.driver.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.ReactiveDatabaseSelectionProvider;
import org.springframework.data.neo4j.core.transaction.Neo4jTransactionManager;
import org.springframework.data.neo4j.core.transaction.ReactiveNeo4jTransactionManager;

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
public class Neo4jConfig
{
	// 配置反应式事务管理器
	@Bean
	public ReactiveNeo4jTransactionManager reactiveTransactionManager(Driver driver,
			ReactiveDatabaseSelectionProvider databaseNameProvider)
	{
		return new ReactiveNeo4jTransactionManager(driver, databaseNameProvider);
	}
	// 配置传统的事务管理器
	@Bean
	public Neo4jTransactionManager transactionManager(Driver driver,
			DatabaseSelectionProvider databaseNameProvider)
	{
		return new Neo4jTransactionManager(driver, databaseNameProvider);
	}
}
