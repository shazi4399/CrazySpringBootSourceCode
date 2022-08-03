package org.crazyit.app.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.crazyit.app.redis.FkRedisUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

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
public class MiaoshaSender
{
	private final AmqpTemplate amqpTemplate;

	public MiaoshaSender(AmqpTemplate amqpTemplate) {this.amqpTemplate = amqpTemplate;}

	public void sendMiaoshaMessage(MiaoshaMessage miaoshaMessage) throws JsonProcessingException
	{
		// 将MiaoshaMessage转换成字符串
		String msg = FkRedisUtil.beanToString(miaoshaMessage);
		// 发送消息
		amqpTemplate.convertAndSend(MQConfig.MIAOSHA_QUEUE, msg);
	}
}
