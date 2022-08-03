package org.crazyit.app;

import org.crazyit.app.handler.ChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

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
@Configuration
public class WebSocketConfig
{
	@Bean
	public HandlerMapping webSocketMapping(ChatHandler chatHandler)
	{
		// 使用HashMap定义WebSocket处理器与URL之间的对应关系
		Map<String, WebSocketHandler> map = new HashMap<>(1);
		map.put("/websocket/{name}", chatHandler);
		// 注册WebSocket处理器
		return new SimpleUrlHandlerMapping(map, -1);
	}

	@Bean
	public WebSocketHandlerAdapter handlerAdapter()
	{
		return new WebSocketHandlerAdapter();
	}
}
