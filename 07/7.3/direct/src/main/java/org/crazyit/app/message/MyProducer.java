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
	public final static String EXCHANGE_NAME = "fkjava.direct";
	final static String[] ROUING_KEYS = {"info", "warning", "error"};

	public static void main(String[] args) throws IOException, TimeoutException
	{
		// 使用自动关闭资源的try语句管理Connection、Channel
		try (
				// 创建与RabbitMQ服务器的TCP连接
				Connection connection = ConnectionUtil.getConnection();
				// 创建Channel
				Channel channel = connection.createChannel())
		{
			//  声明Exchange，指定该Exchange的类型是direct
			channel.exchangeDeclare(EXCHANGE_NAME,
					BuiltinExchangeType.DIRECT,
					true/*是否持久化*/,
					true/*是否自动删除*/, null);
			channel.queueDeclare(Consumer1.QUEUE_NAME, true/* 是否持久化 */,
					false/* 是否独享 */, true/* 是否自动删除 */, null);
			// 为队列1只绑定1个路由key
			channel.queueBind(Consumer1.QUEUE_NAME,
					EXCHANGE_NAME, ROUING_KEYS[2], null);
			channel.queueDeclare(Consumer2.QUEUE_NAME, true/* 是否持久化 */,
					false/* 是否独享 */, true/* 是否自动删除 */, null);
			// 用循环为队列2绑定3个路由key
			for (var i = 0; i < ROUING_KEYS.length; i++)
			{
				channel.queueBind(Consumer2.QUEUE_NAME,
						EXCHANGE_NAME, ROUING_KEYS[i], null);
			}
			for (var i = 1; i < 31; i++)
			{
				// 根据i的值动态决定路由key
				var routingKey = i < 11 ? ROUING_KEYS[0] :
						(i < 21 ? ROUING_KEYS[1] : ROUING_KEYS[2]);
				String msg = "第" + i + "条消息";
				// 向指定Exchange发送消息，路由key为空字符串
				channel.basicPublish(EXCHANGE_NAME, routingKey,
						null, msg.getBytes(StandardCharsets.UTF_8));
				System.out.println("已发送的消息：" + msg);
			}
		}
	}
}