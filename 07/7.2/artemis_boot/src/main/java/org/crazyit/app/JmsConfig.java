package org.crazyit.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

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
@Configuration
public class JmsConfig
{
	@Bean
	public DefaultJmsListenerContainerFactory myFactory(
			DefaultJmsListenerContainerFactoryConfigurer configurer,
			ConnectionFactory connectionFactory)
	{
		DefaultJmsListenerContainerFactory factory =
				new DefaultJmsListenerContainerFactory();
		// 使用与自动配置相同的属性来配置监听器容器工厂（factory）
		configurer.configure(factory, connectionFactory);
		// 设置使用Pub-Sub消息模型
		factory.setPubSubDomain(true);
		return factory;
	}
}