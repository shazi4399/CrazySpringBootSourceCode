package org.crazyit.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
public class CrazyitListener implements ServletContextListener
{
	private static final Logger LOG = LoggerFactory.getLogger(CrazyitFilter.class);
	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		LOG.info("----Web应用初始化完成----");
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
		LOG.info("----Web应用销毁之前----");
	}
}
