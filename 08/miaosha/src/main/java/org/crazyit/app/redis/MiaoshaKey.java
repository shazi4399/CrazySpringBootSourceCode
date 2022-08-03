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
public class MiaoshaKey extends AbstractPrefix
{
	public MiaoshaKey(int expireSeconds, String prefix)
	{
		super(expireSeconds, prefix);
	}
	public static MiaoshaKey miaoshaVerifyCode = new MiaoshaKey(300, "vc");
	// 动态的秒杀路径每60秒就会过期
	public static MiaoshaKey miaoshaPath = new MiaoshaKey(60, "mp");
	// 0代表永不过期
	public static MiaoshaKey isItemOver = new MiaoshaKey(0, "over");
}
