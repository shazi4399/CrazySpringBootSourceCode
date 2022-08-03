package org.crazyit.app.dao;

import org.crazyit.generated.tables.records.UserInfRecord;
import org.jooq.Record2;

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
public interface UserDao
{
	int save(UserInfRecord user);

	int updateById(String name, String password, Integer id);

	List<UserInfRecord> findByNameAndPassword(String name, String password);

	List<UserInfRecord> findByAgeBetween(int startAge, int endAge);

	List<String> findNameByAgeGreatThan(int startAge);

	List<Record2<String, String>> findNamePasswordByAgeLessThan(int endAge);
}
