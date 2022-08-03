package org.crazyit.app;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import java.net.BindException;

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
public class MyAnalyzer extends AbstractFailureAnalyzer<BindException>
{
	@Override
	public FailureAnalysis analyze(Throwable rootFailure, BindException cause)
	{
		cause.printStackTrace();
		return new FailureAnalysis("程序启动出错，程序绑定的端口被占用:"
				+ cause.getMessage(),
				"请先停止占用8080端口的程序后再运行本应用或使用" +
						"server.port改变本应用的端口", cause);
	}
}
