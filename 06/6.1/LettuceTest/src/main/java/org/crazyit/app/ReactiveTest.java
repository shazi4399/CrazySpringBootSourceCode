package org.crazyit.app;

import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import reactor.core.publisher.Mono;

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
public class ReactiveTest
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
	public static void main(String[] args) throws Exception
	{
		init();
		// 创建反应式的RedisReactiveCommands
		RedisReactiveCommands<String, String> redisCommands = conn.reactive();
		// 执行PING命令
		Mono<String> result = redisCommands.ping();
		result.subscribe(System.out::println);
		// 执行ZADD命令
		redisCommands.zadd("myzset", 0.3,
				"Kotlin", 0.5, "Java", 0.4, "Python")
				.subscribe(System.out::println);
		// 执行ZRANK命令
		redisCommands.zrank("myzset", "Java")
				.subscribe(System.out::println);
		// 执行ZRANGE命令
		redisCommands.zrange("myzset", 1, 2)
				.subscribe(System.out::println);
		// 执行ZPOPMAX命令
		redisCommands.zpopmax("myzset")
				.subscribe(System.out::println);
		Thread.sleep(500);
		closeResource(); // 关闭资源
	}
}
