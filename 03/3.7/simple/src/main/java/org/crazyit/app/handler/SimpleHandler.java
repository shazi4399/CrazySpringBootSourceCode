package org.crazyit.app.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
public class SimpleHandler implements WebSocketHandler
{
	@Override
	public Mono<Void> handle(WebSocketSession session)
	{
		// 接收消息流
		Flux<WebSocketMessage> output = session.receive()
				// 把消息（Flux中的数据）恢复成WebSocketMessage
				.map(value -> session.textMessage("回复：" + value.getPayloadAsText()));
		// 发送消息流
		return session.send(output);
	}
}
