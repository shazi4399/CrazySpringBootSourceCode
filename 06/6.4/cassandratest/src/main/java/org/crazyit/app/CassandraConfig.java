package org.crazyit.app;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.ReactiveSessionFactory;
import org.springframework.data.cassandra.SessionFactory;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.ReactiveCqlTemplate;

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
public class CassandraConfig
{
	// 在容器中配额制ReactiveCqlTemplate
	@Bean
	public ReactiveCqlTemplate reactiveCqlTemplate(ReactiveSessionFactory sf)
	{
		return new ReactiveCqlTemplate(sf);
	}
	// 在容器中配额制CqlTemplate
	@Bean
	public CqlTemplate cqlTemplate(SessionFactory sf)
	{
		return new CqlTemplate(sf);
	}
}
