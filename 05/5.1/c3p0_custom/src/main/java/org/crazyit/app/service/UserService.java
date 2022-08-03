package org.crazyit.app.service;

import org.crazyit.app.dao.UserDao;
import org.crazyit.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

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
@Service
public class UserService implements CommandLineRunner
{
	@Autowired
	private UserDao userDao;
	@Autowired
	private DataSource dataSource;
	@Override
	public void run(String... args)
	{
		testSave();
	}

	public void testSave()
	{
		System.out.println("------------" + dataSource);
		for (var i = 1; i < 6; i++)
		{
			var user = new User("fkit" + i, '男', 20 + i);
			userDao.save(user);
		}
	}
}
