package org.crazyit.app.service.impl;

import org.crazyit.app.dao.news.NewsDao;
import org.crazyit.app.dao.user.UserDao;
import org.crazyit.app.domain.news.News;
import org.crazyit.app.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
public class AppServiceImpl implements org.crazyit.app.service.AppService
{
	private UserDao userDao;
	private NewsDao newsDao;

	@Autowired
	public AppServiceImpl(UserDao userDao, NewsDao newsDao)
	{
		this.userDao = userDao;
		this.newsDao = newsDao;
	}

	// 全局事务，自动注入容器中的JTA全局事务管理器
	@Transactional
	@Override
	public void saveUserAndNews()
	{
		var user = new User("fkjava", "fkjava21", 20);
		System.out.println("保存后的User实体: " + userDao.save(user));
		var news = new News("疯狂软件", "Spring Boot终极讲义");
		System.out.println("保存后的News实体: " + newsDao.save(news));
	}

	// 全局事务，自动注入容器中的JTA全局事务管理器
	@Transactional(readOnly = true)
	@Override
	public void getUserAndNews()
	{
		userDao.findById(1).ifPresent(System.out::println);
		newsDao.findById(1).ifPresent(System.out::println);
	}

	// 全局事务，自动注入容器中的JTA全局事务管理器
	@Transactional
	@Override
	public void updateUserAndNews()
	{
		System.out.println(userDao.updateNameById("crazyit", 2));
		// 下面代码将会导致异常
		System.out.println(newsDao.updateTitleById(null, 2));
	}
}
