package org.crazyit.app.config;

import org.crazyit.app.access.AccessInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
@Configuration
public class WebConfig implements WebMvcConfigurer
{
	private final UserArgumentResolver userArgumentResolver;
	private final AccessInterceptor accessInterceptor;

	public WebConfig(UserArgumentResolver userArgumentResolver,
			AccessInterceptor accessInterceptor)
	{
		this.userArgumentResolver = userArgumentResolver;
		this.accessInterceptor = accessInterceptor;
	}

	// 注册额外的参数解析器
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
	{
		argumentResolvers.add(userArgumentResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(accessInterceptor);
	}
}
