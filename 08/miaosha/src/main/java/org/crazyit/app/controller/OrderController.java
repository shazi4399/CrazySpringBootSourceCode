package org.crazyit.app.controller;

import org.crazyit.app.access.AccessLimit;
import org.crazyit.app.domain.MiaoshaItem;
import org.crazyit.app.domain.Order;
import org.crazyit.app.domain.User;
import org.crazyit.app.result.CodeMsg;
import org.crazyit.app.result.Result;
import org.crazyit.app.service.MiaoshaService;
import org.crazyit.app.vo.OrderDetailVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
@RequestMapping("/order")
public class OrderController
{
	private final MiaoshaService miaoshaService;
	public OrderController(MiaoshaService miaoshaService)
	{
		this.miaoshaService = miaoshaService;
	}

	@GetMapping("/detail")
	@ResponseBody
	@AccessLimit // 限制该方法必须登录才能访问
	public Result<OrderDetailVo> detail(User user,
			@RequestParam("orderId") long orderId)
	{
		// 根据订单ID和用户ID获取订单
		Order order = miaoshaService.getOrderByIdAndOwnerId(orderId,
				user.getId());
		// 如果订单为null，表明还没有订单
		if (order == null)
		{
			return Result.error(CodeMsg.ORDER_NOT_EXIST);
		}
		long itemId = order.getItemId();
		MiaoshaItem item = miaoshaService.getMiaoshaItemById(itemId);
		// 用OrderDetailVo封装订单、订单关联的商品和订单对应的用户
		var orderDetailVo = new OrderDetailVo();
		orderDetailVo.setOrder(order);
		orderDetailVo.setMiaoshaItem(item);
		orderDetailVo.setUser(user);
		// 返回OrderDetailVo
		return Result.success(orderDetailVo);
	}
}
