package org.crazyit.app;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.Arrays;
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
@Configuration(proxyBeanMethods = false)
public class KafkaConfig
{
	@Configuration(proxyBeanMethods = false)
	// 启用Kafka流
	@EnableKafkaStreams
	public static class KafkaStreamsConfiguration
	{
		public final static String SOURCE_TOPIC = "replic";
		public final static String SINK_TOPIC = "test1";
		@Bean
		// 通过自动注入的StreamBuilder来创建KStream
		public KStream<String, String> kStream(StreamsBuilder builder)
		{
			KStream<String, String> stream = builder
					.stream(SOURCE_TOPIC);
			// 设置对消息进行处理的处理器
			stream.flatMapValues((ValueMapper<String, Iterable<String>>)
					value -> Arrays.asList(value.split("\\W+")))
					// to()方法指定目标主题
					.to(SINK_TOPIC);
			// 创建Topology对象（其实已不需要创建该对象，此处只是为了方便查看流的拓扑关系）
			System.out.println(builder.build().describe());
			// 直接返回KStream就行了
			return stream;
		}
	}
}
