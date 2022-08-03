package org.crazyit.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
	// 根据user_id查询user_inf表的记录
	@Select("select user_id as id, nickname, password, salt, head, " +
			"register_date as registerDate, last_login_date as lastLoginDate, " +
			"login_count as loginCount from user_inf where user_id = #{id}")
	User findById(long id);
	// 更新user_inf表的记录
	@Update("update user_inf set last_login_date = #{lastLoginDate}" +
			", login_count=#{loginCount} where user_id = #{id}")
	void update(User user);
}
