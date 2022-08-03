package org.crazyit.app.message;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

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
@Component
public class TopicListener2
{
	@KafkaListener(topics = "test1", groupId="groupB")
	public void processMessage(ConsumerRecord<String, String> message)
	{
		System.out.println("从test1收到消息，其key为:" + message.key()
				+ "，其value为:" + message.value());
	}
}