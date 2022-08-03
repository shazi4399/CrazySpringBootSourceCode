package org.crazyit.app.dao;

import org.crazyit.app.domain.UserInf;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

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
// PagingAndSortingRepository继承了CrudRepository，它增加了排序和分页的功能
public interface UserInfDao extends PagingAndSortingRepository<UserInf, Integer>,
		UserInfDaoCustom
{
	// 下面几个方法都是方法名关键字查询
	List<UserInf> findByName(String name);

	List<UserInf> findByPasswordLike(String passPattern);

	List<UserInf> findByAgeBetween(int start, int end);

	List<UserInf> findByNameContainsAndPasswordStartsWith(String subName, String passPrefix);

	// 通过@Query指定查询语句
	@Query("select * from user_inf where name like :namePattern and age > :minAge")
	List<UserInf> findBySql(String namePattern, int minAge);

	@Query("update user_inf set name = :name where user_id = :id")
	@Modifying // 增加@Modifying注解表明该方法会修改数据
	int updateNameById(String name, Integer id);
}
