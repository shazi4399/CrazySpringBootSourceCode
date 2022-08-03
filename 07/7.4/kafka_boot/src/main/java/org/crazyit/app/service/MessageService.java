package org.crazyit.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
	public static final String TOPIC = "test1";
	private final KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public MessageService(KafkaTemplate<String, String> kafkaTemplate)
	{
		this.kafkaTemplate = kafkaTemplate;
	}

	public void produce(String key, String message)
	{
		if (Objects.nonNull(key))
		{
			// 发送消息
			this.kafkaTemplate.send(TOPIC, key, message);
		} else
		{
			// 发送不带key的消息
			this.kafkaTemplate.send(TOPIC, message);
		}
	}
}


