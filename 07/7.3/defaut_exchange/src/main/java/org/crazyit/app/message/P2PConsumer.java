package org.crazyit.app.message;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

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
public class P2PConsumer
{
	final static String QUEUE_NAME = "firstQueue";
	public static void main(String[] args) throws IOException, TimeoutException
	{
		// 创建与RabbitMQ服务器的TCP连接
		Connection connection = ConnectionUtil.getConnection();
		// 创建Channel
		Channel channel = connection.createChannel();
		// 声明Queue，如果该Queue不存在，会自动创建该Queue
		channel.queueDeclare(QUEUE_NAME, true/* 是否持久化 */,
				false/* 是否独享 */, true/* 是否自动删除 */, null);
		// 创建消费者
		Consumer consumer = new DefaultConsumer(channel)
		{
			// 每当读到消息队列中的消息时，该方法将会被自动触发
			// Envelope参数代表信息封包，可获得Exchange名，和路由key
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body)
			{
				String message = new String(body, StandardCharsets.UTF_8);
				System.out.println(envelope.getExchange() + ","
						+ envelope.getRoutingKey() + "," + message);  // ①
			}
		};
		// 从指定队列中获取消息
		channel.basicConsume(QUEUE_NAME, true/* 自动确认 */, consumer);
	}
}
