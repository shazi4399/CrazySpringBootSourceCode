package org.crazyit.app.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@Component
public class ChatHandler implements WebSocketHandler, CorsConfigurationSource
{
	public static Map<WebSocketSession, FluxSink<WebSocketMessage>>
			socketMap = new ConcurrentHashMap<>();
	// WebSocket建立连接时激发该方法
	@Override
	public Mono<Void> handle(WebSocketSession session)
	{
		// 获取URL字符串中的用户名
		var path = session.getHandshakeInfo().getUri().getPath();
		var name = path.substring(path.lastIndexOf("/") + 1);
		// 接收消息流
		Mono<Void> input = session.receive()
				//  Flux<WebSocketMessage>转换成Flux<String>
				.map(msg -> name + ": " + msg.getPayloadAsText())
				// 对每条消息进行处理（将消息发送给所有保存的输出流）
				.doOnNext(msg -> {
					// 遍历系统保存的全部WebSocketSession
					for (var s : socketMap.keySet())
					{
						// 通过WebSocketSession获取对应的FluxSink，然后使用FluxSink来发送消息
						socketMap.get(s).next(s.textMessage(msg));
					}
				}).then();
		// 创建要发送的消息流
		Flux<WebSocketMessage> source = Flux.create(sink -> socketMap.put(session, sink));
		// 发送消息流
		Mono<Void> output = session.send(source);  // ①
		// 将input、output两个Mono合并后返回
		return Mono.zip(input, output).then();
	}

	@Override
	public CorsConfiguration getCorsConfiguration(ServerWebExchange exchange)
	{
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		return configuration;
	}
}