package org.crazyit.app.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.crazyit.app.domain.MiaoshaItem;
import org.crazyit.app.domain.MiaoshaOrder;
import org.crazyit.app.domain.User;
import org.crazyit.app.redis.FkRedisUtil;
import org.crazyit.app.service.MiaoshaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
public class MiaoshaReceiver
{
	private	final MiaoshaService miaoshaService;

	public MiaoshaReceiver(MiaoshaService miaoshaService)
	{
		this.miaoshaService = miaoshaService;
	}

	@RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
	public void receive(String message) throws JsonProcessingException
	{
		// 将字符串类型的消息转换成MiaoshaMessage对象
		MiaoshaMessage miaoshaMessage = FkRedisUtil
				.stringToBean(message, MiaoshaMessage.class);
		// 获取秒杀用户
		User user = miaoshaMessage.getUser();
		// 获取秒杀商品的ID
		long itemId = miaoshaMessage.getItemId();
		// 获取秒杀商品
		MiaoshaItem item = miaoshaService.getMiaoshaItemById(itemId);
		int stock = item.getStockCount();
		// 如果秒杀商品的库存小于0，无法继续秒杀，直接返回
		if (stock <= 0)
		{
			return;
		}
		// 从Redis缓存根据用户ID和商品ID读取秒杀订单
		MiaoshaOrder miaoshaOrder = miaoshaService
				.getMiaoshaOrderByUserIdAndItemId(user.getId(), itemId);
		// 如果秒杀订单存在，说明用户正尝试重复秒杀，无需处理，因此直接返回
		if (miaoshaOrder != null)
		{
			return;
		}
		// 调用MiaoshaService的miaosha()方法执行秒杀
		miaoshaService.miaosha(user, item);
	}
}
