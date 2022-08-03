package org.apache.app;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

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
public class Producer
{
	public final static String TOPIC = "test1";

	public static void main(String[] args)
	{
		var props = new Properties();
		// 指定Kafka的节点地址
		props.put("bootstrap.servers",
				"localhost:9092,localhost:9093,localhost:9094");
		// 指定确认机制，默认值是0。
		props.put("acks", "all");  // ①
		// 指定发送失败后的重试次数
		props.put("retries", 0);
		// 当多条消息要发送到同一分区时，生产者将尝试对多条消息进行批处理，
		// 从而减少网络请求数，这有助于提高客户机和服务器的性能。
		// 该参数控制默认的批处理的数据大小
		props.put("batch.size", 16384);
		// 指定消息key的序列化器
		props.put("key.serializer", StringSerializer.class.getName());
		// 指定消息value的序列化器
		props.put("value.serializer", StringSerializer.class.getName());
		try (
				// 创建消息生产者
				var producer = new KafkaProducer<String, String>(props))
		{
			for (var messageNo = 1; messageNo < 101; messageNo++)
			{
				var msg = "你好，这是第" + messageNo + "条消息";
				if (messageNo < 51)
				{
					// 发送带消息
					producer.send(new ProducerRecord<>(TOPIC, "fkjava", msg));
				} else
				{
					// 发送不带key的消息
					producer.send(new ProducerRecord<>(TOPIC, msg));
				}
				// 每生产了20条消息输出一次
				if (messageNo % 20 == 0)
				{
					System.out.println("发送的信息:" + msg);
				}
			}
		}
	}
}