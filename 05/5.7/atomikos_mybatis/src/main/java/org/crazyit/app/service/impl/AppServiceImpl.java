package org.crazyit.app.service.impl;

import org.crazyit.app.dao.news.NewsMapper;
import org.crazyit.app.dao.user.UserMapper;
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
	private UserMapper userMapper;
	private NewsMapper newsMapper;

	@Autowired
	public AppServiceImpl(UserMapper userMapper, NewsMapper newsMapper)
	{
		this.userMapper = userMapper;
		this.newsMapper = newsMapper;
	}
	// 全局事务，自动注入容器中Primary的JTA全局事务管理器
	@Transactional
	@Override
	public void saveUserAndNews()
	{
		var user = new User("fkjava", "fkjava123", 21);
		userMapper.save(user);
		var news = new News("Spring Boot终极讲义",
				"关于Spring Boot的终极之书，精通本书整合的各种技术后，你就是Java大神");
		newsMapper.save(news);
	}

	// 全局事务，自动注入容器中Primary的JTA全局事务管理器
	@Transactional
	@Override
	public void updateUserAndNews()
	{
		System.out.println(newsMapper.updateTitleById("Spring Boot新书上市", 2));
		// 下面代码引发异常
		System.out.println(userMapper.updateNameById(null, 2));
	}
}
