package org.crazyit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
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
	// 创建YamlPropertySourceLoader，用于加载YAML文件
	private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment,
			SpringApplication application)
	{
		// 指定自定义的配置文件
		Resource path = new ClassPathResource("fk/fk.yml");
		// 加载自定义配置文件
		PropertySource<?> ps = null;
		try
		{
			ps = this.loader.load("custom-resource", path).get(0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("fkjava.name: " + ps.getProperty("fkjava.name"));
		System.out.println("fkjava.age: " +ps.getProperty("fkjava.age"));
		System.out.println("fkjava.servers[0]: " +ps.getProperty("fkjava.servers[0]"));
		System.out.println("fkjava.servers[1]: " +ps.getProperty("fkjava.servers[1]"));
		// 将PropertySource中的属性添加到Environment配置环境中
		environment.getPropertySources().addLast(ps);
	}
}