package org.crazyit.app;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

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
public class Publisher
{
	static RedisClient redisClient;
	// 定义连接池
	static GenericObjectPool<StatefulRedisPubSubConnection<String, String>> pool;
	// 初始化RedisClient、连接池的方法
	public static void init()
	{
		// 创建RedisURI对象
		RedisURI redisUri = RedisURI.builder()
				.withHost("localhost")
				.withPassword(new char[]{'3', '2', '1', '4', '7'})
				.withDatabase(0)
				.withPort(6379)
				.withTimeout(Duration.of(10, ChronoUnit.SECONDS))
				.build();
		// 创建RedisClient
		redisClient = RedisClient.create(redisUri);
		var conf = new GenericObjectPoolConfig<StatefulRedisPubSubConnection
				<String, String>>();
		conf.setMaxTotal(20); // 设置允许的最大连接数
		// 创建连接池对象（其中连接由redisClient的connectPubSub方法创建）
		pool = ConnectionPoolSupport.createGenericObjectPool(
				redisClient::connectPubSub, conf);
	}
	public static void closeResource()
	{
		// 关闭连接池
		pool.close();
		// 关闭RedisClient
		redisClient.shutdown();
	}
	public static void main(String[] args) throws Exception
	{
		init();
		// 调用borrowObject()方法获取连接
		RedisPubSubCommands<String, String> pubCommands = pool.borrowObject().sync();
		pubCommands.publish("mychannel", "I want to learn Redis");
		closeResource();
	}
}
