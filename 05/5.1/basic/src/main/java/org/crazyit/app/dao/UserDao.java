package org.crazyit.app.dao;

import org.crazyit.app.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

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
public interface UserDao extends PagingAndSortingRepository<User, Integer>
{
}
