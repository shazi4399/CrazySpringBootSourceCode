package org.crazyit.app.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.crazyit.app.dao.UserDao;
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
@Repository
public class UserDaoImpl implements UserDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	// 使用Lambda表达式创建按一个RowMapper<User>对象，
	// 它的作用就是将ResultSet的一行转换成User对象
	private final RowMapper<User> mapper = (rs, rowNum) -> {
		var user = new User(rs.getString("name"),
				rs.getString("password"), rs.getInt("age"));
		user.setId(rs.getInt("user_id"));
		return user;
	};

	@Override
	public User get(Integer id)
	{
		var sql = "select * from user_inf" +
				" where user_id = ?";
		// 调用queryForObject执行查询语句
		return this.jdbcTemplate.queryForObject(sql, mapper, id);
	}
	@Override
	public int save(User user)
	{
		var sql = "insert into user_inf values (null, ?, ?, ?)";
		// 调用update执行DML语句
		return this.jdbcTemplate.update(sql, user.getName(),
				user.getPassword(), user.getAge());
	}
	@Override
	public int update(User user)
	{
		var sql = "update user_inf set name = ?, " +
				"password = ?, age = ? where user_id = ?";
		// 调用update执行DML语句
		return this.jdbcTemplate.update(sql, user.getName(),
				user.getPassword(), user.getAge(), user.getId());
	}
	@Override
	public int delete(User user)
	{
		var sql = "delete from user_inf where user_id = ?";
		// 调用update执行DML语句
		return this.jdbcTemplate.update(sql, user.getId());
	}
	@Override
	public int delete(Integer id)
	{
		var sql = "delete from user_inf where user_id = ?";
		// 调用update执行DML语句
		return this.jdbcTemplate.update(sql, id);
	}
	@Override
	public List<String> findNameByAgeBetween(int startAge, int endAge)
	{
		var sql = "select name from user_inf" +
				" where age between ? and ?";
		// 调用queryForList执行查询语句，查询返回单列数据
		return this.jdbcTemplate.queryForList(sql,
				String.class, startAge, endAge);
	}
	@Override
	public List<User> findByNameLike1(String name)
	{
		var sql = "select * from user_inf" +
				" where name like ?";
		// 调用query执行查询语句
		// 使用mapper将每行数据转换成User对象
		return this.jdbcTemplate.query(sql, mapper, "%" + name + "%");
	}

	@Override
	public List<User> findByNameLike2(String name)
	{
		var sql = "select user_id id, name, password, age from user_inf" +
				" where name like ?";
		// 调用query执行查询语句
		// 使用BeanPropertyRowMapper对象将每行数据转换成User对象
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>
				(User.class), "%" + name + "%");
	}
}
