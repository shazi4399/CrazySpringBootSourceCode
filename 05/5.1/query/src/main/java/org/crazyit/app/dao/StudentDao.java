package org.crazyit.app.dao;

import org.crazyit.app.domain.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

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
public interface StudentDao extends CrudRepository<Student, Integer>
{
	// 定义该方法使用JPQL语句查询
	@Query("select s from Student s where s.clazz.name like ?1")
	List<Student> findByClazzNameJPQL(String clazzName);

	// 此处使用的是SQL语句，添加nativeQuery = true
	@Query(value = "select s.* from student_inf s join clazz_inf c" +
			" on s.clazz_code = c.clazz_code where c.name like ?1",
			nativeQuery = true)
	List<Student> findByClazzNameNative(String clazzName);

	// 本查询方法使用的JPQL语句, 只查询指定列（投影）
	// 多列以数组返回
	@Query("select s.name, s.gender from Student s " +
			"where s.clazz.name like ?1")
	List<String[]> findNameAndGenderByClazzNameJPQL1(String clazzName);

	// 本查询方法使用的JPQL语句, 只查询指定列（投影）
	// 多列以List返回
	@Query("select s.name, s.gender from Student s " +
			"where s.clazz.name like ?1")
	List<List<String>> findNameAndGenderByClazzNameJPQL2(String clazzName);

	// 本查询方法使用的JPQL语句, 只查询指定列（投影）
	// 多列默认以Object[]数组返回
	@Query("select s.name, s.gender from Student s where s.clazz.name like ?1")
	List<Object> findNameAndGenderByClazzNameJPQL3(String clazzName);

	// 本查询方法使用的JPQL语句, 只查询指定列（投影）
	// 多列以Map返回，列别名将作为key
	@Query("select s.name as nm, s.gender as gender from " +
			"Student s where s.clazz.name like ?1")
	List<Map<String, Object>> findNameAndGenderByClazzNameJPQL4(String clazzName);

	// 使用命名参数
	@Query("select s.name, s.gender from Student s " +
			"where s.clazz.name like :clazz_name")
	List<String[]> findNameAndGenderByClazzNameJPQL5(
			@Param("clazz_name") String clazzName);

	@Modifying // 修改数据要添加该注解，执行该方法需要事务
	@Query("update Clazz c set c.name = ?1 where c.id > ?2")
	int updateClazzNameById(String name, Integer id);

	@Modifying // 删除数据要添加该注解，执行该方法需要事务
	@Query("delete from Clazz c where c.id > ?1")
	int deleteClazzById(Integer id);

	// 使用命名JPQL查询
	List<Student> namedJpql(String clazzNamePattern, char gender);

	// 使用命名SQL查询
	List<Student> namedSql(String clazzNamePattern);
}
