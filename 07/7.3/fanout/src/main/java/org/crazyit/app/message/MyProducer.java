package org.crazyit.app.message;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

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
public class MyProducer
{
	public final static String EXCHANGE_NAME = "fkjava.fanout";
	public final static String ROUING_KEY = "test1";
	public static void main(String[] args) throws IOException, TimeoutException
	{
		// 使用自动关闭资源的try语句管理Connection、Channel
		try (
				// 创建与RabbitMQ服务器的TCP连接
				Connection connection = ConnectionUtil.getConnection();
				// 创建Channel
				Channel channel = connection.createChannel())
		{
			//  声明Exchange，指定该Exchange的类型是fanout
			channel.exchangeDeclare(EXCHANGE_NAME,
					BuiltinExchangeType.FANOUT,
					true/*是否持久化*/,
					false/*是否自动删除*/, null);
			// 声明并绑定两个Queue，如果它们不存在，则自动创建这些Queue
			channel.queueDeclare(Consumer1.QUEUE_NAME, true/* 是否持久化 */,
					false/* 是否独享 */, true/* 是否自动删除 */, null);
			channel.queueBind(Consumer1.QUEUE_NAME,
					EXCHANGE_NAME, ROUING_KEY, null);
			channel.queueDeclare(Consumer2.QUEUE_NAME, true/* 是否持久化 */,
					false/* 是否独享 */, true/* 是否自动删除 */, null);
			channel.queueBind(Consumer2.QUEUE_NAME,
					EXCHANGE_NAME, ROUING_KEY, null);
			for (var i = 1; i < 11; i++)
			{
				String msg = "第" + i + "条消息";
				// 向指定Exchange发送消息，路由key为空字符串
				channel.basicPublish(EXCHANGE_NAME, "",
						null, msg.getBytes(StandardCharsets.UTF_8));
				System.out.println("已发送的消息：" + msg);
			}
		}
	}
}

