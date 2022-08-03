package org.crazyit.app.dao;

import java.util.List;

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
public interface UserDao
{
	User get(Integer id);

	int save(User user);

	int update(User user);

	int delete(User user);

	int delete(Integer id);

	List<String> findNameByAgeBetween(int startAge, int endAge);

	List<User> findByNameLike1(String name);

	List<User> findByNameLike2(String name);
}
