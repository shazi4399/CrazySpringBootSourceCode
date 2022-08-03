package org.crazyit.app.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class MQConfig
{
	public static final String MIAOSHA_QUEUE = "miaosha.queue";
	// 配置Queue，对应于消息队列
	@Bean
	public Queue queue()
	{
		return new Queue(MIAOSHA_QUEUE, true);
	}
}
