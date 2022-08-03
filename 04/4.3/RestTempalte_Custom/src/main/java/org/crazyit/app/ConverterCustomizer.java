package org.crazyit.app;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
public class ConverterCustomizer implements RestTemplateCustomizer
{
	@Override
	public void customize(RestTemplate restTemplate)
	{
		System.out.println("---设置消息转换器---");
		// 创建自定义的HttpMessageConverter
		var fastJson = new FastJsonHttpMessageConverter();
		// 设置FastJsonHttpMessageConverter支持的各种MediaType
		fastJson.setSupportedMediaTypes(List.of(MediaType.APPLICATION_JSON,
				MediaType.APPLICATION_XML));
		// 设置使用第三方的消息转换器
		restTemplate.setMessageConverters(List.of(fastJson));
	}
}