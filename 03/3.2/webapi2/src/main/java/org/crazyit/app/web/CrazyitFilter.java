package org.crazyit.app.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

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
public class CrazyitFilter implements Filter
{
	private static final Logger LOG = LoggerFactory.getLogger(CrazyitFilter.class);
	@Override
	public void doFilter(ServletRequest requ, ServletResponse resp,
			FilterChain filterChain) throws IOException, ServletException
	{
		LOG.info("处理请求之前的过滤处理");
		// 放行请求，继续让目标Servlet（或其他Web组件）处理用户请求
		filterChain.doFilter(requ, resp);
		LOG.info("处理请求之后的过滤处理");
	}
}
