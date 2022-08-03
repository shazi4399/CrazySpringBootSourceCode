package org.crazyit.app.config;

import org.crazyit.app.access.UserContext;
import org.crazyit.app.domain.User;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

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
public class UserArgumentResolver implements HandlerMethodArgumentResolver
{
	// 该方法的返回true则表明要解析该参数
	@Override
	public boolean supportsParameter(MethodParameter methodParameter)
	{
		// 获取要解析的参数类型
		Class<?> clazz = methodParameter.getParameterType();
		// 只有当该返回值为true时，才会调用下面的resolveArgument方法解析参数
		return clazz == User.class;
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer modelAndViewContainer,
			NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory)
	{
		// 将UserContext的getUser()方法的返回值作为User参数的值
		return UserContext.getUser();
	}
}