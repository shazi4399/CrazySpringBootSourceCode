package org.crazyit.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class UploadFilePathConfig implements WebMvcConfigurer
{
	@Value("${file.static-access-path}")
	private String staticAccessPath;
	@Value("${file.upload-folder}")
	private String uploadFolder;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		// 将本地磁盘的指定路径映射成Spring Boot的静态资源路径
		registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
	}
}