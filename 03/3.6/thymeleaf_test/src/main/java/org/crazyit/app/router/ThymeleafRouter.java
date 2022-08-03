package org.crazyit.app.router;

import org.crazyit.app.handler.ThymeleafHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

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
public class ThymeleafRouter
{
	@Bean
	public RouterFunction<ServerResponse> route(ThymeleafHandler handler)
	{
		return RouterFunctions
				// 定义映射地址和处理器方法之间的对应关系
				.route(RequestPredicates.POST("/login")
						.and(RequestPredicates.accept(MediaType.TEXT_HTML)), handler::login)
				.andRoute(RequestPredicates.GET("/viewBook/{id}")
						.and(RequestPredicates.accept(MediaType.TEXT_HTML)), handler::viewBook);
	}
}
