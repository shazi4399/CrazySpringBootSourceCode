package org.crazyit.app.comp;

import org.springframework.boot.web.server.Compression;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

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
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>
{
	@Override
	public void customize(ConfigurableServletWebServerFactory server)
	{
		// 设置端口
		server.setPort(8181);
		// 设置该服务器的context Path
		server.setContextPath("/mytest");
		Compression compression = new Compression();
		compression.setMinResponseSize(DataSize.ofBytes(1024));
		// 设置启用HTTP响应压缩
		server.setCompression(compression);
	}
}