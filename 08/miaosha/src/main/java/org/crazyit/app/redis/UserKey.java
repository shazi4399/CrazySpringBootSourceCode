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
public class UserKey extends AbstractPrefix
{
	public static final String COOKIE_NAME_TOKEN = "token";
	public static final int TOKEN_EXPIRE = 1800;

	public UserKey(int expireSeconds, String prefix)
	{
		super(expireSeconds, prefix);
	}
	// 定义用于保存分布式Session ID的key
	public static UserKey token = new UserKey(TOKEN_EXPIRE, "token");
	// 0代表永不过期
	public static UserKey getById = new UserKey(0, "id");
	// 用于保存验证码的key
	public static UserKey verifyCode = new UserKey(300, "vc");
}
