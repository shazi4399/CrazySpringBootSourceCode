package org.crazyit.app.message;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
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
public class ConnectionUtil
{
	public static Connection getConnection() throws IOException, TimeoutException
	{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("root");
		factory.setPassword("32147");
		// 如果不设置虚拟主机，则使用默认虚拟主机（/）
//		factory.setVirtualHost("fkjava-vhosts");
		// 创建与RabbitMQ服务器的TCP连接
		return factory.newConnection();
	}
}
