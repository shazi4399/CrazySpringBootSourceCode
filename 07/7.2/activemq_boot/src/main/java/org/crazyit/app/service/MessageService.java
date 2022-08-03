package org.crazyit.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jms.core.JmsTemplate;
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
	private final JmsTemplate jmsTemplate;

	@Autowired
	public MessageService(JmsTemplate jmsTemplate)
	{
		this.jmsTemplate = jmsTemplate;
	}

	public void produce(String message)
	{
		// 使用P2P消息模型
		this.jmsTemplate.setPubSubDomain(false);
		this.jmsTemplate.convertAndSend("myQueue", message);
	}

	public void publish(String message)
	{
		// 使用Pub-Sub消息模型
		this.jmsTemplate.setPubSubDomain(true);
		this.jmsTemplate.convertAndSend("myTopic", message);
	}
}
