package org.crazyit.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

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
public class LocaleConfig implements WebMvcConfigurer
{
	@Value("${locale-param}")
	private String localeParam;
	@Value("${locale-resolver-type}")
	private String resolverType;

	// 定义LocaleResolver解析器
	@Bean
	public LocaleResolver localeResolver()
	{
		if (resolverType.equals("session"))
		{
			var localeResolver = new SessionLocaleResolver();
			// 设置默认区域
			localeResolver.setDefaultLocale(Locale.CHINA);
			return localeResolver;
		}
		else if (resolverType.equals("cookie"))
		{
			var localeResolver = new CookieLocaleResolver();
			// 设置默认区域
			localeResolver.setDefaultLocale(Locale.CHINA);
			// 设置Cookie的名字
			localeResolver.setCookieName("lang");
			// 设置Cookie的最大寿命
			localeResolver.setCookieMaxAge(3600 * 24);
			return localeResolver;
		}
		else
		{
			// 依然使用默认的AcceptHeaderLocaleResolver解析器
			return new AcceptHeaderLocaleResolver();
		}
	}

	// 定义LocaleChangeInterceptor拦截器
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor()
	{
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		// 设置参数名
		lci.setParamName(localeParam);
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		// 注册LocaleChangeInterceptor拦截器
		registry.addInterceptor(localeChangeInterceptor());
	}
}