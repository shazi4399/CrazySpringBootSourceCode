package org.crazyit.funny.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
// 定义属性处理类
@ConfigurationProperties(prefix = FunnyProperties.FUNNY_PREFIX)
public class FunnyProperties
{
	public static final String FUNNY_PREFIX = "org.crazyit.funny";
	private String dest;
	private String charset;

	public String getDest()
	{
		return dest;
	}

	public void setDest(String dest)
	{
		this.dest = dest;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}
}
