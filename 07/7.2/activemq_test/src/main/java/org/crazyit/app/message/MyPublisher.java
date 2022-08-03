package org.crazyit.app.message;

import org.apache.activemq.ActiveMQConnectionFactory;

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
public class MyPublisher
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
		// 创建JMS会话
		Session session = conn.createSession(false/*不是事务性会话*/,
				Session.AUTO_ACKNOWLEDGE);
		// 创建消息目的（主题），指定消息主题的名称
		Destination dest = session.createTopic("myTopic");
		// 创建消息生产者
		MessageProducer producer = session.createProducer(dest);
		// 向消息主题发送10个文本消息数据
		for (var i = 1; i <= 10; i++)
		{
			// 创建文本消息
			TextMessage msg = session.createTextMessage("第" + i + "个主题消息");
			// 设置消息属性
			msg.setStringProperty("ContentType", "txt");
			// 发送消息
			producer.send(msg);
			// 在本地打印消息
			System.out.println("已发送的消息：" + msg.getText());
		}
		// 关闭资源
		session.close();
		conn.close();
	}
}