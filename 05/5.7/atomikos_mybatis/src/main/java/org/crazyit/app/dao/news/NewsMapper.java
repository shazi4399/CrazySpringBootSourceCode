package org.crazyit.app.dao.news;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.crazyit.app.domain.news.News;

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
@Mapper
public interface NewsMapper
{
	@Insert("insert into news_inf(news_title, news_content)" +
			" values(#{title}, #{content})")
	Integer save(News user);

	@Update("update news_inf set news_title = #{title} where news_id = #{id}")
	Integer updateTitleById(String title, Integer id);
}
