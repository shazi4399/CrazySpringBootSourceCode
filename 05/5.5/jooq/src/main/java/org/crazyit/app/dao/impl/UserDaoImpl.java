package org.crazyit.app.dao.impl;

import org.crazyit.app.dao.UserDao;
import org.crazyit.generated.Tables;
import org.crazyit.generated.tables.records.UserInfRecord;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository
public class UserDaoImpl implements UserDao
{
	// jOOQ官方文档爱用create保存DSLContext，我们也这么干
	private DSLContext create;
	@Autowired
	public UserDaoImpl(DSLContext dslContext)
	{
		this.create = dslContext;
	}

	@Override
	public int save(UserInfRecord user)
	{
		// 执行INSERT INTO语句
		return this.create.insertInto(Tables.USER_INF)
				.values(null, user.getName(),
						user.getPassword(), user.getAge())
				.execute();
	}
	@Override
	public int updateById(String name, String password, Integer id)
	{
		// 执行UPDATE语句
		return this.create.update(Tables.USER_INF)
				.set(Tables.USER_INF.NAME, name)
				.set(Tables.USER_INF.PASSWORD, password)
				.where(Tables.USER_INF.USER_ID.eq(id))
				.execute();
	}
	@Override
	public List<UserInfRecord> findByNameAndPassword(String name, String password)
	{
		// 执行SELECT ... FROM语句
		return this.create.selectFrom(Tables.USER_INF)
				// 有多少个条件，直接在where方法中列出来即可
				.where(Tables.USER_INF.NAME.equal(name),
						Tables.USER_INF.PASSWORD.equal(password))
				// fetch默认抓取整个对象
				.fetch();
	}
	@Override
	public List<UserInfRecord> findByAgeBetween(int startAge, int endAge)
	{
		// 执行SELECT ... FROM语句
		return this.create.selectFrom(Tables.USER_INF)
				//  用where方法添加where条件
				.where(Tables.USER_INF.AGE.between(startAge).and(endAge))
				.fetch();

	}
	@Override
	public List<String> findNameByAgeGreatThan(int startAge)
	{
		// 执行SELECT ... FROM语句
		return this.create.selectFrom(Tables.USER_INF)
				// 有多少个条件，直接在where方法中列出来即可
				.where(Tables.USER_INF.AGE.gt(startAge))
				// 只抓取指定的属性
				.fetch(Tables.USER_INF.NAME);
	}
	@Override
	public List<Record2<String, String>> findNamePasswordByAgeLessThan(int endAge)
	{
		// 执行SELECT ... FROM语句
		return this.create.select(Tables.USER_INF.NAME, Tables.USER_INF.PASSWORD)
				.from(Tables.USER_INF)
				// 有多少个条件，直接在where方法中列出来即可
				.where(Tables.USER_INF.AGE.lt(endAge))
				// 只抓取指定的属性
				.fetch();
	}
}
