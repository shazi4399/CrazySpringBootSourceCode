package org.crazyit.app;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

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
public class SyncTest
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
	public static void main(String[] args)
	{
		init();
		// 创建StatefulRedisConnection
		StatefulRedisConnection<String, String> conn = redisClient.connect();
		// 创建同步模式的RedisCommands
		RedisCommands<String, String> redisCommands = conn.sync();
		// SetArgs，主要用于设置超时时长
		SetArgs setArgs = SetArgs.Builder.nx().ex(2000);
		// 执行PING命令
		System.out.println(redisCommands.ping());
		// 执行SET命令
		String result1 = redisCommands.set("name", "疯狂软件", setArgs);
		System.out.println(result1);
		// 执行HSET命令
		Long result2 = redisCommands.hset("user", Map.of("name",
				"Crazyit", "age", "23", "address", "广州"));
		System.out.println(result2);
		// 执行GET命令
		String result3 = redisCommands.get("name");
		System.out.println(result3);
		// 执行HGET命令
		System.out.println(redisCommands.hget("user", "address"));
		closeResource(); // 关闭资源
	}
}
