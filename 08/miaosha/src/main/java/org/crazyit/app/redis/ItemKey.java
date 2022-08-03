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
public class ItemKey extends AbstractPrefix
{
	public ItemKey(int expireSeconds, String prefix)
	{
		super(expireSeconds, prefix);
	}
	// 缓存秒杀商品列表页面的key前缀
	public static ItemKey itemList = new ItemKey(120, "list");
	// 缓存秒杀商品库存的key前缀
	public static ItemKey miaoshaItemStock = new ItemKey(0, "stock");
}
