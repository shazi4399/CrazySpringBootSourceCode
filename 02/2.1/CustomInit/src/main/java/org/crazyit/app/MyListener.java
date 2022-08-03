package org.crazyit.app;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

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
// 定义事件监听器，负责监听ApplicationStartedEvent事件
// 实现ApplicationContextAware接口，可用于获取所在的Spring容器
public class MyListener implements ApplicationContextAware,
		ApplicationListener<ApplicationStartedEvent>
{
	private ApplicationContext ctx;
	@Override
	public void onApplicationEvent(ApplicationStartedEvent event)
	{
		// 获取激发事件的容器
		ConfigurableApplicationContext c = event.getApplicationContext();
		if (c == ctx)
		{
			System.out.println("-----激发事件的容器与监听器所在容器相同-----");
		}
		// 后面的代码可插入任意自定义的处理
		System.out.println("========执行自定义处理=======");
	}

	// 接口注入方法，通过该方法可访问Spring容器
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException
	{
		this.ctx = ctx;
	}
}
