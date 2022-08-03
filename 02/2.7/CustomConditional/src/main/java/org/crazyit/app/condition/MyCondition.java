package org.crazyit.app.condition;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
public class MyCondition implements Condition
{
	@Override
	public boolean matches(ConditionContext context,
			AnnotatedTypeMetadata metadata)
	{
		// 获取@ConditionalCustom注解的全部属性
		Map<String, Object> map = metadata.getAnnotationAttributes(
				ConditionalCustom.class.getName());
		// 获取注解的value属性值（String[]数组）
		String[] vals = (String[]) map.get("value");
		Environment env = context.getEnvironment();
		// 遍历每个属性值
		for (Object val : vals)
		{
			// 如果某个属性值对应的配置属性不存在，返回false
			if (env.getProperty(val.toString()) == null)
			{
				return false;
			}
		}
		return true;
	}
}

