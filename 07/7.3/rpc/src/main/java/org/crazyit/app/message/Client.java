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
public class Client
{
	public static void main(String[] args) throws IOException, TimeoutException
	{
		// 创建与RabbitMQ服务器的TCP连接
		Connection connection = ConnectionUtil.getConnection();
		// 创建Channel
		Channel channel = connection.createChannel();
		// 声明一个由RabbitMQ命名的、独享的、会自动删除的队列
		String replyQueue = channel.queueDeclare().getQueue();
		// 创建消费者
		Consumer consumer = new DefaultConsumer(channel)
		{
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body)
			{
				String message = new String(body, StandardCharsets.UTF_8);
				// 取出correlationId，用于获取发出消息的ID
				var correlationId = properties.getCorrelationId();
				System.out.println(correlationId + "返回的消息为："
					+ message);
			}
		};
		// 等待从指定队列获取消息
		channel.basicConsume(replyQueue, true, consumer);
		// 采用循环发送10条消息
		for (var i = 1; i < 10; i++)
		{
			// 使用AMQP.BasicProperties封装消息属性
			AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
					.replyTo(replyQueue)
					.correlationId(i + "")
					.deliveryMode(2) // 使用持久化消息
					.build();
			// 向默认Exchange发送消息，使用rpc_queue作为路由key
			channel.basicPublish("", Server.SERVER_QUEUE,
					props, (i + "").getBytes(StandardCharsets.UTF_8));
		}
	}
}