package org.crazyit.app;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

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
@Configuration(proxyBeanMethods = false)
public class FkConfiguration
{
	@Bean
	public HttpMessageConverters customConverters()
	{
		// 创建自定义的HttpMessageConverter
		var fastJson = new FastJsonHttpMessageConverter();
		// 设置FastJsonHttpMessageConverter支持的各种MediaType
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XML);
		supportedMediaTypes.add(MediaType.IMAGE_GIF);
		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
		supportedMediaTypes.add(MediaType.IMAGE_PNG);
		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.TEXT_XML);
		fastJson.setSupportedMediaTypes(supportedMediaTypes);
		// 创建配置对象
		var config = new FastJsonConfig();
		// 为FastJsonConfig设置各种特性，准备供FastJsonHttpMessageConverter使用
		config.setSerializerFeatures(
				// 禁用循环检测
				SerializerFeature.DisableCircularReferenceDetect,
				// 输出Map的空value值
				SerializerFeature.WriteMapNullValue,
				// 输出格式良好的JSON字符串
				SerializerFeature.PrettyFormat
		);
		fastJson.setFastJsonConfig(config);
		// 通过HttpMessageConverters设置使用自定义的HttpMessageConverter
		return new HttpMessageConverters(fastJson);
	}
}