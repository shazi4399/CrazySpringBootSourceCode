package org.crazyit.funny.autoconfigure;

import org.crazyit.funny.io.WriterTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.sql.SQLException;

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
// 当WriterTemplate类存在时配置生效
@ConditionalOnClass(WriterTemplate.class)
// 启用FunnyProperties属性处理类
@EnableConfigurationProperties(FunnyProperties.class)
// 让该自动配置位于DataSourceAutoConfiguration自动配置之后处理
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class FunnyAutoConfiguration
{
	// FunnyProperties类负责加载配置属性
	private final FunnyProperties properties;

	public FunnyAutoConfiguration(FunnyProperties properties)
	{
		this.properties = properties;
	}

	@Bean(destroyMethod = "close")
	// 当单例的DataSource Bean存在时配置生效
	@ConditionalOnSingleCandidate(DataSource.class)
	// 只有当容器中没有WriterTemplate Bean时，该配置才会生效
	@ConditionalOnMissingBean
	// 通过@AutoConfigureOrder注解指定该配置方法
	// 比下一个配置WriterTemplate的方法的优先级更高
	@AutoConfigureOrder(99)
	public WriterTemplate writerTemplate(DataSource dataSource) throws SQLException
	{
		return new WriterTemplate(dataSource);
	}

	@Bean(destroyMethod = "close")
	// 只有当前面的WriterTemplate配置没有生效时，该方法的配置才会生效
	@ConditionalOnMissingBean
	@AutoConfigureOrder(199)
	public WriterTemplate writerTemplate2() throws FileNotFoundException
	{
		File f = new File(this.properties.getDest());
		Charset charset = Charset.forName(this.properties.getCharset());
		return new WriterTemplate(f, charset);
	}
}
