package org.crazyit.app.message;

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
public class P2PProducer
{
	public static void main(String[] args) throws IOException, TimeoutException
	{
		// 使用自动关闭资源的try语句管理Connection、Channel
		try (
				// 创建与RabbitMQ服务器的TCP连接
				Connection connection = ConnectionUtil.getConnection();
				// 创建Channel
				Channel channel = connection.createChannel())
		{
			// 声明Queue，如果该Queue不存在，会自动创建该Queue
			channel.queueDeclare(P2PConsumer.QUEUE_NAME, true/* 是否持久化 */,
					false/* 是否独享 */, true/* 是否自动删除 */, null);
			for (var i = 1; i < 11; i++)
			{
				String msg = "第" + i + "条消息";
				// 最后一个参数是消息体
				channel.basicPublish("", P2PConsumer.QUEUE_NAME/* 路由key */,
						null, msg.getBytes(StandardCharsets.UTF_8));
				System.out.println("已发送的消息：" + msg);
			}
		}
	}
}
