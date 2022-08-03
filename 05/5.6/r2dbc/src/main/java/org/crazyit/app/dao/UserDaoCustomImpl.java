package org.crazyit.app.dao;

import io.r2dbc.spi.Row;
import org.crazyit.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

import java.util.function.Function;

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
public class UserDaoCustomImpl implements UserDaoCustom
{
	@Autowired
	private DatabaseClient dbClient;
	private Function<Row, User> mappingFunc = row -> {
		var user = new User(row.get("name", String.class),
				row.get("password", String.class),
				row.get("age", Integer.class));
		user.setId(row.get("user_id", Integer.class));
		return user;
	};
	@Override
	public Flux<User> customQuery1(int startAge, int endAge)
	{
		return dbClient.sql("select * from user_inf where age between :0 and :1" )
				.bind(0, startAge)
				.bind(1, endAge)
				.map(mappingFunc)
				.all();
	}
	@Override
	public Flux<User> customQuery2(int startAge, String passPattern)
	{
		return dbClient.sql("select * from user_inf where age > :0 and password like :1" )
				.bind(0, startAge)
				.bind(1, passPattern)
				.map(mappingFunc)
				.all();
	}
}
