package org.crazyit.app.dao.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.crazyit.app.domain.user.User;

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
	@Insert("insert into user_inf values(null, #{name}, #{password}, #{age})")
	Integer save(User user);

	@Update("update user_inf set name = #{name} where user_id = #{id}")
	Integer updateNameById(String name, Integer id);
}
