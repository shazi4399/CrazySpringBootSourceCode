package org.crazyit.app;

import org.crazyit.app.domain.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
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
	public User user()
	{
		return new User("fkjava", true);
	}
	@Bean
	// 只有当user.active表达式为true时，该方法才生效
	@ConditionalOnExpression("user.active")
	public DateFormat dateFormat()
	{
		return DateFormat.getDateInstance();
	}
}
