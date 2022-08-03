package org.crazyit.app.dao;

import org.crazyit.app.domain.Author;
import org.crazyit.app.domain.AuthorId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

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
public interface AuthorDao extends CrudRepository<Author, AuthorId>
{
	// 定义根据分区key执行查询的方法
	Optional<Author> findByIdId(Integer id);
}
