package org.crazyit.app.listener;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
public class ContainerEventListener
		implements ApplicationListener<WebServerInitializedEvent>
{
	@Override
	public void onApplicationEvent(WebServerInitializedEvent evt)
	{
		System.out.println("动态HTTP端口为："
				+ evt.getWebServer().getPort());
	}
}
