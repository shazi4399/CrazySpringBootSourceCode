package org.crazyit.app;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

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
public class MyConfig
{
	// 在容器中配置一个YamlPropertiesFactoryBean
	@Bean
	public YamlPropertiesFactoryBean fkProps()
	{
		var factory = new YamlPropertiesFactoryBean();
		factory.setResources(new ClassPathResource("fk/fk.yml"));
		return factory;
	}
}
