package org.crazyit.app.dao;

import org.crazyit.app.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
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
// 该类叫StudentDaoImpl或StudentDaoCustomImpl都可以
public class StudentDaoCustomImpl implements StudentDaoCustom
{
	// 依赖注入JPA的EntityManager
	@Autowired
	private EntityManager em;

	@Override
	public List<Student> customQuery(String namePattern)
	{
		// 直接用JPA的EntityManager来执行查询
		return em.createQuery("select s from Student s where s.name like ?1", Student.class)
				.setParameter(1, namePattern)
				.getResultList();
	}
	@Override
	public List<Student> customSqlQuery(int startAge, int endAge)
	{
		// 直接用JPA的EntityManager来执行原生SQL查询
		return em.createNativeQuery("select s.* from student_inf s " +
				"where s.age between ?1 and ?2", Student.class)
				.setParameter(1, startAge)
				.setParameter(2, endAge)
				.getResultList();
	}
}
