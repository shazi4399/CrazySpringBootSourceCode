package org.crazyit.app.service;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class MessageService
{
	public static final String EXCHANGE_NAME = "boot.fanout";
	public static final String[] QUEUE_NAMES = {"myQueue1", "myQueue2"};
	private final AmqpAdmin amqpAdmin;
	private final AmqpTemplate amqpTemplate;

	@Autowired
	public MessageService(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate)
	{
		this.amqpAdmin = amqpAdmin;
		this.amqpTemplate = amqpTemplate;
		// 创建Exchange对象，根据Exchange类型的不同
		// 可使用DirectExchange、FanoutExchange、
		// HeadersExchange、TopicExchange不同的实现类
		var exchange = new FanoutExchange(EXCHANGE_NAME,
				true/* 是否持久化 */, true/* 是否自动删除 */);
		// 声明Exchange
		this.amqpAdmin.declareExchange(exchange);
		// 使用循环声明、并绑定了两个队列
		for (String queueName : QUEUE_NAMES)
		{
			var queue = new Queue(queueName, true/* 是否持久化 */,
					false/* 是否独享 */, true/* 是否自动删除 */);
			// 声明队列
			this.amqpAdmin.declareQueue(queue);
			var binding = new Binding(queueName,
					Binding.DestinationType.QUEUE/* 指定绑定的目的为队列 */,
					EXCHANGE_NAME, ""/* 路由key */, null);
			// 声明绑定
			this.amqpAdmin.declareBinding(binding);
		}
	}

	public void produce(String message)
	{
		this.amqpTemplate.convertAndSend(EXCHANGE_NAME,
				""/* 路由key */, message);
	}
}


