package org.crazyit.app;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
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
public class Subscriber
{
	static RedisClient redisClient;
	static StatefulRedisConnection<String, String> conn;
	// 定义连接池
	static GenericObjectPool<StatefulRedisPubSubConnection<String, String>> pool;
	// 初始化RedisClient、StatefulRedisConnection和连接池的方法
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
		// 获取StatefulRedisConnection
		conn = redisClient.connect();
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
		// 关闭StatefulRedisConnection
		conn.close();
		// 关闭RedisClient
		redisClient.shutdown();
	}
	public static void main(String[] args) throws Exception
	{
		init();
		RedisCommands<String, String> redisCommands = conn.sync();
		// 设置redisCommands只通知key过期（Ex）的事件
		redisCommands.configSet("notify-keyspace-events", "Ex");
		// 设置该key-value于3秒之后过期，该过期消息会被订阅者收到
		redisCommands.setex("organization", 3, "疯狂软件");
		// 通过连接池获取连接
		var subConn = pool.borrowObject();
		subConn.addListener(new RedisPubSubAdapter<>()	  // ①
		{
			@Override
			// 订阅channel成功时触发该方法
			public void subscribed(String channel, long count)
			{
				System.out.println("订阅成功，channel: " + channel + ", count:" + count);
			}
			// 收到channel的消息时触发该方法
			@Override
			public void message(String channel, String message)
			{
				System.out.println("channel:" + channel
						+ ", message: " + message);
			}
			// 订阅pattern的channel成功时触发该方法
			@Override
			public void psubscribed(String pattern, long count)
			{
				System.out.println("订阅成功，pattern: " + pattern + ", count:" + count);
			}
			// 收到pattern的channel的消息时触发该方法
			@Override
			public void message(String pattern, String channel, String message)
			{
				System.out.println("pattern:" + pattern + ", channel:" + channel
						+ ", message: " + message);
			}
		});
		RedisPubSubCommands<String, String> subCommands = subConn.sync();
		// 订阅pattern模式的channel
		subCommands.psubscribe("__keyevent@0__:expired");
		// 订阅channel
		subCommands.subscribe("mychannel");
		Thread.sleep(50000);
		closeResource();
	}
}
