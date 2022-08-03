package org.crazyit.app.dao;

import org.crazyit.app.domain.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

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
public interface StudentDao extends CrudRepository<Student, Integer>
{
	List<Student> findByAddress(String addr);

	List<Student> findByAgeGreaterThan(int start);

	List<Student> findByNameStartsWith(String namePrefix);

	List<Student> findByAgeBetweenAndAddress(int start, int end, String addr);

	// 使用属性路径的形式根据关联实体的属性进行查询
	List<Student> findByClazzNameLike(String clazzPattern);
	// 根据地址删除实体
	int deleteByAddress(String addr);

	// 传入分页参数进行分页查询
	// 通过传入Pageable、Sort等参数，即使用CrudRespository同样能进行分页、排序等
	List<Student> findByClazzNameLike(String clazzPattern, Pageable pageable);

	// 同时根据多个属性、及关联属性执行查询
	List<Student> findByGenderAndAgeBetweenOrClazzNameLike(char gender,
			int start, int end, String clazzPattern);

}
