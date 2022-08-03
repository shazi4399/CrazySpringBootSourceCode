package org.crazyit.app.dao;

import org.crazyit.app.domain.Clazz;
import org.crazyit.app.domain.Student;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class StudentDaoTest
{
	@Autowired
	private StudentDao studentDao;

	@ParameterizedTest
	@ValueSource(strings = {"男", "女"})
	public void testSpecification1(char gender)
	{
		studentDao.findAll((Specification<Student>) (root, query, cb) -> {
			// root.get("gender")表示获取gender这个字段名称，equal表示执行equal查询
			// 相当于 select s from Student s where s.gender = ?1
			return cb.equal(root.get("gender"), gender);
		}).forEach(System.out::println);
	}
	@ParameterizedTest
	@MethodSource("studentList")
	public void testSpecification2(Student stu)
	{
		studentDao.findAll((Specification<Student>) (root, query, cb) -> {
			// 定义集合，用于收集动态的查询条件
			List<Predicate> predicates = new ArrayList<>();
			if (stu != null)
			{
				// 是否传入了姓名来查询
				if (StringUtils.hasText(stu.getName()))
				{
					predicates.add(cb.like(root.get("name"),
							"%" + stu.getName() + "%"));
				}
				// 是否传入了地址来查询
				if (StringUtils.hasText(stu.getAddress()))
				{
					predicates.add(cb.like(root.get("address"),
							"%" + stu.getAddress() + "%"));
				}
				// 是否传入了性别来查询
				if (stu.getGender() != '\0')
				{
					predicates.add(cb.equal(root.get("gender"),
							stu.getGender()));
				}
				// 判断是否传入了班级信息来查询
				if (stu.getClazz() != null && StringUtils.hasText(stu.getClazz().getName()))
				{
					root.join("clazz", JoinType.INNER);
					Path<String> clazzName = root.get("clazz").get("name");
					predicates.add(cb.like(clazzName,
							"%" + stu.getClazz().getName() + "%"));
				}
			}
			return cb.and(predicates.toArray(new Predicate[0]));
		}).forEach(System.out::println);
	}
	private static Stream<Student> studentList()
	{
		var s1 = new Student("牛", null, 0, '\0');
		var s2 = new Student("杏", "木仙", 0, '\0');
		var s3 = new Student("孙", "花", 0, '男');
		var s4 = new Student("蜘蛛", "盘丝", 0, '女');
		s4.setClazz(new Clazz("基础班"));
		return Stream.of(s1, s2, s3, s4);
	}
}
