package org.crazyit.app.messsage;

import org.springframework.core.annotation.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

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
@Component
public class QueueListener
{
	@JmsListener(destination = "myQueue")
	public void processMessage(String content)
	{
		System.out.println("收到队列消息：" + content);
	}
}