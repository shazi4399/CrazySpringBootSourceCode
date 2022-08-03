package org.crazyit.app.dao.news;

import org.crazyit.app.domain.news.News;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
public interface NewsDao extends CrudRepository<News, Integer>
{
	@Query("update News n set n.title = ?1 where n.id = ?2")
	@Modifying
	Integer updateTitleById(String title, Integer id);
}
