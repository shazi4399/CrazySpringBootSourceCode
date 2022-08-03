package org.apache.app;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

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
public class ConsumerB
{
	// 定义消费的主题
	public final static String TOPIC = "test1";
	// 定义该消费者实例所属的组ID
	private static final String GROUPID = "groupA";
	private static KafkaConsumer<String, String> consumer;

	public static void main(String[] args) throws InterruptedException
	{
		// 启动一条新线程来处理程序退出
		new Thread(() ->
		{
			var scanner = new Scanner(System.in);
			if (scanner.nextLine().equals(":exit"))
			{
				if (consumer != null)
				{
					// 取消订阅
					consumer.unsubscribe();
					// 关闭消费者
					consumer.close();
				}
				System.exit(0);
			}
		}).start();
		var props = new Properties();
		// 指定Kafka的节点地址
		props.put("bootstrap.servers",
				"localhost:9092,localhost:9093,localhost:9094");
		// 指定消费者组ID
		props.put("group.id", GROUPID);
		// 设置是否自动提交offset
		props.put("enable.auto.commit", "true");
		// 设置自动提交offset的时间间隔
		props.put("auto.commit.interval.ms", "1000");
		// session超时时长
		props.put("session.timeout.ms", "30000");
		// 程序读取消息的初始offset
		props.put("auto.offset.reset", "latest");
		// 指定消息key的反序列化器
		props.put("key.deserializer", StringDeserializer.class.getName());
		// 指定消息value的反序列化器
		props.put("value.deserializer", StringDeserializer.class.getName());
		consumer = new KafkaConsumer<>(props);
		// 订阅主题
		consumer.subscribe(Arrays.asList(TOPIC));
		System.out.println("---------开始消费---------");
		while (true)
		{
			// 拉取消息
			ConsumerRecords<String, String> msgList = consumer.poll(Duration.ofMillis(100));
			if (null != msgList && msgList.count() > 0)
			{
				// 遍历取得的消息
				for (ConsumerRecord<String, String> record : msgList)
				{
					System.out.println("收到消息: key = " + record.key() + ", value = "
							+ record.value() + " offset = " + record.offset());
				}
			} else
			{
				Thread.sleep(1000);
			}
		}
	}
}