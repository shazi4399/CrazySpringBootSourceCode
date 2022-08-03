package org.crazyit.app.dao;

import org.crazyit.app.domain.Author;
import org.springframework.data.repository.CrudRepository;

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
public interface SyncAuthorDao extends CrudRepository<Author, Long>
{
	Author findByName(String name);
}
