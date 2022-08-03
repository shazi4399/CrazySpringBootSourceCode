package org.crazyit.app.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.crazyit.app.domain.User;
import org.crazyit.app.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService
{
	// 定义一个List模拟系统中所有User对象
	private static List<User> userList = Collections
			.synchronizedList(new ArrayList<>());
	static {
		userList.add(new User(1, "sun", "32145"));
		userList.add(new User(2, "zhu", "83433"));
		userList.add(new User(3, "crazyit.org", "leegang"));
	}
	@Override
	public Integer userLogin(User user)
	{
		// 如果能找到目标User对象，则返回该User的id
		for (User u : userList)
		{
			if (u.equals(user))
			{
				return u.getId();
			}
		}
		return -1;
	}
}
