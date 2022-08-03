package org.crazyit.app.dao;

import org.crazyit.app.domain.User;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
public interface UserDao extends ReactiveCrudRepository<User, Integer>,
		UserDaoCustom
{
	// 下面几个方法都是方法名关键字查询
	Flux<User> findByName(String name);

	Flux<User> findByPasswordLike(String passPattern);

	Flux<User> findByAgeBetween(int start, int end);

	Flux<User> findByNameContainsAndPasswordStartsWith(String subName, String passPrefix);

	// 方法名关键字删除
	Mono<Integer> deleteByNameLike(String namePattern);

	// 通过@Query指定查询语句
	@Query("select * from user_inf where name like :namePattern and age > :minAge")
	Flux<User> findBySql(String namePattern, int minAge);

	@Query("update user_inf set name = :name where user_id = :id")
	@Modifying	// 增加@Modifying注解表明该方法会修改数据
	Mono<Integer> updateNameById(String name, Integer id);
}
