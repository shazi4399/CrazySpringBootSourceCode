package org.crazyit.app.util;

import java.util.UUID;

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
public class UUIDUtil
{
	public static String uuid()
	{
		return UUID.randomUUID().toString().replace("-", "");   //去掉原生自带的"-"
	}
}
