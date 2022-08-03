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
public class Server
{
	public static final String SERVER_QUEUE = "rpc_queue";
	public static void main(String[] args) throws IOException, TimeoutException
	{
		// 创建与RabbitMQ服务器的TCP连接
		Connection connection = ConnectionUtil.getConnection();
		// 创建Channel
		Channel channel = connection.createChannel();
		// 声明服务器消费的独享队列，用于创建该队列
		channel.queueDeclare(SERVER_QUEUE, true,
				true, true, null);
		// 创建消费者
		Consumer consumer = new DefaultConsumer(channel)
		{
			// 每当读到消息队列中的消息时，该方法将会被自动触发
			// Envelope参数代表信息封包，可获得Exchange名，和路由key
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body) throws IOException
			{
				int number = Integer.parseInt(new String(body, StandardCharsets.UTF_8));
				// 获取发送应答消息的队列
				var replyQueue = properties.getReplyTo();
				// 获取correlation_id属性
				var correlationId = properties.getCorrelationId();
				// 向默认Exchange发送消息，使用replyQueue作为路由key，
				// 该消息将被分发给replyQueue队列
				channel.basicPublish("", replyQueue,
						new AMQP.BasicProperties.Builder()
								.correlationId(correlationId + "")
								.build(),
						// 以服务器计算的的结果作为消息体
						(cal(number) + "").getBytes(StandardCharsets.UTF_8));

			}
		};
		// 读取SERVER_QUEUE队列的消息
		channel.basicConsume(SERVER_QUEUE, true, consumer);
	}
	// 模拟服务器端的计算功能
	public static int cal(int n)
	{
		int result = 1;
		for (var i = 2; i <= n; i++)
		{
			result *= i;
		}
		return result;
	}
}