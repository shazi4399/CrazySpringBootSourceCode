package org.crazyit.app.redis;

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
public abstract class AbstractPrefix implements KeyPrefix
{
	private final int expireSeconds;
	private final String prefix;

	public AbstractPrefix(String prefix)
	{
		// 小于0代表永不过期
		this(-1, prefix);
	}

	public AbstractPrefix(int expireSeconds, String prefix)
	{
		// 设置过期时间
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}

	@Override
	public int expireSeconds()
	{
		return expireSeconds;
	}

	// getPrefix将会返回“类名:prefix”的形式
	@Override
	public String getPrefix()
	{
		String className = getClass().getSimpleName();
		return className + ":" + prefix;
	}
}