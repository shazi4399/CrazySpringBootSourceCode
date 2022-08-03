package org.crazyit.app.rabbitmq;

import org.crazyit.app.domain.User;

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
public class MiaoshaMessage
{
	private User user;
	private long itemId;

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public long getItemId()
	{
		return itemId;
	}

	public void setItemId(long itemId)
	{
		this.itemId = itemId;
	}
}
