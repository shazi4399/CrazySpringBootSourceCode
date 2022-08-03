package org.apache.app;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.ValueMapper;

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
public class Pipe
{
	public final static String SOURCE_TOPIC = "replic";
	public final static String SINK_TOPIC = "test1";
	private static KafkaStreams streams;
	public static void main(String[] args)
	{
		// 启动一条新线程来处理程序退出
		new Thread(() ->
		{
			var scanner = new Scanner(System.in);
			if (scanner.nextLine().equals(":exit"))
			{
				if (streams != null)
				{
					// 关闭流
					streams.close();
				}
				System.exit(0);
			}
		}).start();
		var props = new Properties();
		// 程序的唯一标识符，用于区别于其他应用程序与同一Kafka集群通信
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
		// 指定Kafka的节点地址
		props.put("bootstrap.servers",
				"localhost:9092,localhost:9093,localhost:9094");
		// 指定消息key默认的序列化和反序列化器
		props.put("default.key.serde", Serdes.String().getClass());
		// 指定消息value默认的序列化和反序列化器
		props.put("default.value.serde", Serdes.String().getClass());
		// 创建StreamsBuilder
		final var builder = new StreamsBuilder();
		// stream()方法指定源主题
		builder.<String, String>stream(SOURCE_TOPIC)
			// 设置对消息进行处理的处理器（可换成Lambda表达式）
			.mapValues(new ValueMapper<String, String>()
			{
				@Override
				public String apply(String value)
				{
					return "疯狂Java:" + value;
				}
			})
			// 设置对消息进行处理的处理器（可换成Lambda表达式）
			.flatMapValues(new ValueMapper<String, Iterable<String>>()
			{
				@Override
				public Iterable<String> apply(String value)
				{
					return Arrays.asList(value.split("\\W+"));
				}
			})
			// to()方法指定目标主题
			.to(SINK_TOPIC);
		// 创建Topology对象
		final Topology topology = builder.build();
		// 输出Topology对象代表的流关系
		System.out.println(topology.describe());
		// 创建KafkaStreams实例
		streams = new KafkaStreams(topology, props);
		// 开始执行“导流”
		streams.start();
	}
}