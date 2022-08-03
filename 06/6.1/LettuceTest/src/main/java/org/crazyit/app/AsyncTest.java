package org.crazyit.app;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.pubsub.StatefulRedisClusterPubSubConnection;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;
import io.lettuce.core.masterslave.StatefulRedisMasterSlaveConnection;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;

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
public class AsyncTest
{
	static RedisClient redisClient;
	static StatefulRedisConnection<String, String> conn;
	// 初始化RedisClient、StatefulRedisConnection的方法
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
	}
	public static void closeResource()
	{
		// 关闭StatefulRedisConnection
		conn.close();
		// 关闭RedisClient
		redisClient.shutdown();
	}
	public static void main(String[] args) throws InterruptedException
	{
		init();
		// 创建异步模式的RedisAsyncCommands
		RedisAsyncCommands<String, String> redisCommands = conn.async();
		// 执行PING命令
		RedisFuture<String> result = redisCommands.ping();
		result.thenAccept(System.out::println);
		// 执行LPUSH
		redisCommands.lpush("books",
				"疯狂Java讲义", "疯狂Python讲义", "疯狂Java讲义")
				.thenAccept(System.out::println);
		// 执行LINDEX命令
		redisCommands.lindex("books", 1)
				.thenAccept(System.out::println);
		// 执行SADD命令
		redisCommands.sadd("languages",
				"Java", "Python", "Kotlin", "Rust");
		// 执行SRANDMEMBER命令
		redisCommands.srandmember("languages")
				.thenAccept(System.out::println);
		Thread.sleep(500); // 程序暂停0.5秒，让异步任务完成
		closeResource(); // 关闭资源
	}
}