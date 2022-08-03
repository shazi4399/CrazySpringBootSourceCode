package org.crazyit.app;

import org.crazyit.app.condition.ConditionalCustom;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;

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
@Configuration(proxyBeanMethods = false)
public class FkConfig
{
	@Bean
	// 只有当org.fkjava.test和org.crazyit.abc两个配置属性存在时才生效
	@ConditionalCustom({"org.fkjava.test", "org.crazyit.abc"})
	public DateFormat dateFormat()
	{
		return DateFormat.getDateInstance();
	}
}
