package org.crazyit.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.crazyit.app.domain.User;

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
@Mapper
public interface UserMapper
{
	@Select(value="select user_id id, name, password, age from " +
			"user_inf where user_id = #{b}")
	User get(Integer id);

	@Insert("insert into user_inf values "
			+ "(null, #{name}, #{password}, #{age})")
	int save(User user);

	@Update("update user_inf set name=#{name}, "
			+ "password=#{password} where user_id=#{id}")
	int update(User user);

	@Delete("delete from user_inf where user_id=#{a}")
	int delete(Integer id);

	@Select("select user_id id, name, password, age from " +
			"user_inf where age between #{startAge} and #{endAge}")
	List<User> findByAgeBetween(int startAge, int endAge);

	@Select(value="select user_id id, name, password, age from " +
			"user_inf where name like #{name}")
	List<User> findByNameLike(String name);
}
