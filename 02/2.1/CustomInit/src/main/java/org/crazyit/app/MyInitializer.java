package org.crazyit.app;

import org.springframework.context.ApplicationContextInitializer;
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
public class MyInitializer implements
		ApplicationContextInitializer<ConfigurableApplicationContext>
{
	@Override
	public void initialize(ConfigurableApplicationContext configurableApplicationContext)
	{
		// 接下来的代码可对Spring容器执行任意的初始化
		System.out.println("====模拟对Spring容器执行初始化====");
	}
}
