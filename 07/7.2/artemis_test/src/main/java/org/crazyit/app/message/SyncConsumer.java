package org.crazyit.app.message;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;

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
public class SyncConsumer
{
	// 定义消息Broker的URL
	private static final String ACTIVEMQ_URL = "tcp://192.168.1.188:61616";

	public static void main(String[] args) throws JMSException
	{
		// 创建连接工厂（不同客户端的连接工厂类不同）
		ActiveMQConnectionFactory connFactory =
				new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		// 连接工厂创建连接
		Connection conn = connFactory.createConnection("root", "32147");
		// 打开连接
		conn.start();
		// 创建JMS会话
		Session session = conn.createSession(false/*不是事务性会话*/,
				Session.AUTO_ACKNOWLEDGE);
		// 创建消息目的（队列），指定消息队列的名称
		Destination dest = session.createQueue("myQueue");
		// 创建消息消费者
		MessageConsumer consumer = session.createConsumer(dest);
		// 循环接收2条消息
		for (var i = 0; i < 2; i++)
		{
			// 同步接收消息，如果没有接收到消息，该方法会阻塞线程
			TextMessage msg = (TextMessage) consumer.receive();
			System.out.println(msg);
			System.out.println("同步接收到的消息：" + msg.getText());
		}
		// 关闭资源
		session.close();
		conn.close();
	}
}