package org.crazyit.app.vo;

import org.crazyit.app.domain.MiaoshaItem;
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
public class ItemDetailVo
{
	private int remainSeconds = 0;
	private int leftSeconds = 0;
	private MiaoshaItem miaoshaItem;
	private User user;

	public int getRemainSeconds()
	{
		return remainSeconds;
	}

	public void setRemainSeconds(int remainSeconds)
	{
		this.remainSeconds = remainSeconds;
	}

	public int getLeftSeconds()
	{
		return leftSeconds;
	}

	public void setLeftSeconds(int leftSeconds)
	{
		this.leftSeconds = leftSeconds;
	}

	public MiaoshaItem getMiaoshaItem()
	{
		return miaoshaItem;
	}

	public void setMiaoshaItem(MiaoshaItem miaoshaItem)
	{
		this.miaoshaItem = miaoshaItem;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public String toString()
	{
		return "ItemDetailVo{" +
				"remainSeconds=" + remainSeconds +
				", leftSeconds=" + leftSeconds +
				", miaoshaItem=" + miaoshaItem +
				", user=" + user +
				'}';
	}
}