package org.crazyit.app;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class MyBatisConfig
{
	// 在Spring容器主动定义ConfigurationCustomizer Bean
	@Bean
	ConfigurationCustomizer mybatisConfigurationCustomizer() {
		return new ConfigurationCustomizer() {
			@Override
			public void customize(org.apache.ibatis.session.Configuration configuration)
			{
				// 下面即可对Configuration执行设置
				configuration.setCacheEnabled(true);
			}
		};
	}
}
