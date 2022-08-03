package org.crazyit.app.dao;

import org.crazyit.app.domain.UserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class UserInfDaoCustomImpl implements UserInfDaoCustom
{
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<UserInf> customQuery(String passPattern,
			int startAge, int endAge) throws SQLException
	{
		var sql = "select * from user_inf where password " +
				"like ? and age between ? and ?";
		try (
				// 通过dataSource来获取数据连接
				var conn = dataSource.getConnection();
				// 创建PreparedStatement
				var pstmt = conn.prepareStatement(sql);
		)
		{
			pstmt.setString(1, passPattern);
			pstmt.setInt(2, startAge);
			pstmt.setInt(3, endAge);
			// 执行查询
			var rs = pstmt.executeQuery();
			List<UserInf> resultList = new ArrayList<>();
			// 遍历结果集，提取结果集的数据
			while (rs.next())
			{
				var user = new UserInf(rs.getString("name"),
						rs.getString("password"), rs.getInt("age"));
				user.setUserId(rs.getInt("user_id"));
				resultList.add(user);
			}
			rs.close();
			return resultList;
		}
	}
	@Override
	public List<UserInf> jdbcTemplateQuery(int startAge, int endAge)
	{
		var sql = "select * from user_inf where age between ? and ?";
		// 调用JdbcTemplate的query()执行查询
		return this.jdbcTemplate.query(sql, new Object[]{startAge, endAge},
				new BeanPropertyRowMapper<>(UserInf.class));
	}
}
