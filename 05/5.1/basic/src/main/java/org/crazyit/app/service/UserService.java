package org.crazyit.app.service;

import org.crazyit.app.dao.UserDao;
import org.crazyit.app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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
	@Override
	public void run(String... args)
	{
//		testSave();
//		testUpdate();
//		testDelete();
//		testQuery();
//		testPageQuery(2, 2);
		testPageOrderQuery(2, 2);
	}

	public void testSave()
	{
		for (var i = 1; i < 6; i++)
		{
			var user = new User("fkjava" + i, '男', 20 + i);
			userDao.save(user);
		}
	}

	public void testUpdate()
	{
		var user = new User("测试名", '女', 18);
		user.setId(1);
		// 更新实体
		userDao.save(user);
	}

	public void testDelete()
	{
		// 删除实体
		userDao.deleteById(1);
	}

	public void testQuery()
	{
		// 查询指定实体是否存在
		System.out.println("id为2的实体是否存在:" + userDao.existsById(2));
		System.out.println("User实体的数量:" + userDao.count());
		// 查询id为2的实体
		userDao.findById(2).ifPresent(System.out::println);
		// 查询id为3、4的实体
		userDao.findAllById(List.of(3, 4)).forEach(System.out::println);
	}

	public void testPageQuery(int pageIndex, int pageCount)
	{
		// Pageable封装分页信息，PageRequest提供了of()方法来创建Pageable对象，
		Pageable pageable = PageRequest.of(pageIndex - 1, pageCount);
		Page<User> usersPage = userDao.findAll(pageable);
		System.out.println("查询总页数:" + usersPage.getTotalPages());
		System.out.println("查询总记录数:" + usersPage.getTotalElements());
		System.out.println("查询当前第几页:" + (usersPage.getNumber() + 1));
		System.out.println("查询当前页面的记录数:" + usersPage.getNumberOfElements());
		// 查询出的结果数据集合
		usersPage.getContent().forEach(u -> {
			System.out.println(u.getName() + "---->" + u.getAge());
		});
	}
	public void testPageOrderQuery(int pageIndex, int pageCount)
	{
		// 创建带分页功能的Pageable对象
		Pageable pageable = PageRequest.of(pageIndex - 1, pageCount, Sort.Direction.DESC, "age");
		Page<User> usersPage = userDao.findAll(pageable);
		System.out.println("查询总页数:" + usersPage.getTotalPages());
		System.out.println("查询总记录数:" + usersPage.getTotalElements());
		System.out.println("查询当前第几页:" + (usersPage.getNumber() + 1));
		System.out.println("查询当前页面的记录数:" + usersPage.getNumberOfElements());
		// 查询出的结果数据集合
		usersPage.getContent().forEach(u -> {
			System.out.println(u.getName() + "---->" + u.getAge());
		});
	}
}
