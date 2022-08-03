package org.crazyit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

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
public class FkEnvironmentPostProcessor implements EnvironmentPostProcessor
{
	private final PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment,
			SpringApplication application)
	{
		// 指定自定义的配置文件
		Resource path = new ClassPathResource("fk/fk.properties");
		// 加载自定义配置文件
		PropertySource<?> ps = loadProperty(path);
		System.out.println("fkjava.name: " + ps.getProperty("fkjava.name"));
		System.out.println("fkjava.age: " +ps.getProperty("fkjava.age"));
		// 将PropertySource中的属性添加到Environment配置环境中
		environment.getPropertySources().addLast(ps);
	}

	private PropertySource<?> loadProperty(Resource path)
	{
		if (!path.exists())
		{
			throw new IllegalArgumentException("资源: " + path + " 不存在");
		}
		try
		{
			// 加载path对应的配置文件
			return this.loader.load("custom-resource", path).get(0);
		}
		catch (IOException ex)
		{
			throw new IllegalStateException("加载配置文件出现错误: " + path, ex);
		}
	}
}