package org.crazyit.app.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
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
@ServerEndpoint("/websocket/{name}")
public class WebSocketEndpoint
{
	public static Map<Session, String> socketMap = new ConcurrentHashMap<>();
	// 连接建立成功触发的方法
	@OnOpen
	public void onOpen(@PathParam("name") String name, Session session)
	{
		socketMap.put(session, name);
	}
	// 连接关闭时触发的方法
	@OnClose
	public void onClose(Session session)
	{
		socketMap.remove(session);
	}
	// 收到客户端消息后触发的方法
	@OnMessage
	public void onMessage(String message, Session session)
	{
		System.out.printf("收到来自%s的消息:%s%n", session, message);
		try
		{
			var name = socketMap.get(session);
			for (var client: socketMap.keySet())	// ①
			{
				client.getBasicRemote().sendText(name + "说：" + message);
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	// 发生错误时触发的方法
	@OnError
	public void onError(Session session, Throwable error)
	{
		System.out.println("发生错误");
		socketMap.remove(session);
	}
}
