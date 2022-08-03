package org.crazyit.app;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
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
	// 只有当目标平台的Java版本是11或更新的平台时，该方法才生效
	@ConditionalOnJava(value = JavaVersion.ELEVEN,
			range = ConditionalOnJava.Range.EQUAL_OR_NEWER)
	public DateFormat dateFormat()
	{
		return DateFormat.getDateInstance();
	}
}
