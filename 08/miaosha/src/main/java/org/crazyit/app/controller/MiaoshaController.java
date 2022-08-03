package org.crazyit.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.crazyit.app.access.AccessLimit;
import org.crazyit.app.domain.MiaoshaItem;
import org.crazyit.app.domain.MiaoshaOrder;
import org.crazyit.app.domain.User;
import org.crazyit.app.rabbitmq.MiaoshaMessage;
import org.crazyit.app.rabbitmq.MiaoshaSender;
import org.crazyit.app.redis.FkRedisUtil;
import org.crazyit.app.redis.ItemKey;
import org.crazyit.app.result.CodeMsg;
import org.crazyit.app.result.Result;
import org.crazyit.app.service.MiaoshaService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
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
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean
{
	private final MiaoshaService miaoshaService;
	private final FkRedisUtil fkRedisUtil;
	private final MiaoshaSender mqSender;
	// 存放ItemId与秒杀是否结束的对应关系
	private final Map<Long, Boolean> localOverMap =
			Collections.synchronizedMap(new HashMap<>());

	public MiaoshaController(MiaoshaService miaoshaService,
			FkRedisUtil fkRedisUtil, MiaoshaSender mqSender)
	{
		this.miaoshaService = miaoshaService;
		this.fkRedisUtil = fkRedisUtil;
		this.mqSender = mqSender;
	}

	@Override
	public void afterPropertiesSet()
	{
		// 获取所有物品列表
		List<MiaoshaItem> itemList = miaoshaService.listMiaoshaItem();
		if (itemList == null)
		{
			return;
		}
		for (MiaoshaItem item : itemList)
		{
			// 将所有物品及其对应库存放入Redis缓存
			fkRedisUtil.set(ItemKey.miaoshaItemStock, ""
					+ item.getItemId(), item.getStockCount());
			localOverMap.put(item.getId(), false);
		}
	}

	@GetMapping(value = "/verifyCode")
	@ResponseBody
	@AccessLimit // 限制该方法必须登录才能访问
	public void getMiaoshaVerifyCode(HttpServletResponse response,
			User user, @RequestParam("itemId") long itemId) throws IOException
	{
		// 生成验证码
		BufferedImage image = miaoshaService.createVerifyCode(user, itemId);
		OutputStream out = response.getOutputStream();
		// 将验证码输出到客户端
		ImageIO.write(image, "JPEG", out);
		out.flush();
		out.close();
	}

	@GetMapping(value = "/path")
	@ResponseBody
	// 限制该方法必须登录才能访问，且每5秒内只能调用5次
	@AccessLimit(seconds = 5, maxCount = 5)
	public Result<String> getMiaoshaPath(User user,
			@RequestParam("itemId") long itemId,
			@RequestParam(value = "verifyCode",
					defaultValue = "0") int verifyCode)
	{
		// 如果输入的验证码不匹配
		if (!miaoshaService.checkVerifyCode(user, itemId, verifyCode))  // ①
		{
			return Result.error(CodeMsg.REQUEST_ILLEGAL);
		}
		String path = miaoshaService.createMiaoshaPath(user, itemId);
		return Result.success(path);
	}

	@PostMapping("/{path}/proMiaosha")
	@ResponseBody
	@AccessLimit // 限制该方法必须登录才能访问
	public Result<Integer> proMiaosha(Model model, User user,
			@RequestParam("itemId") long itemId,
			@PathVariable("path") String path)
			throws JsonProcessingException
	{
		model.addAttribute("user", user);
		// 验证动态的秒杀地址是否正确
		boolean check = miaoshaService.checkPath(user, itemId, path);   // ②
		if (!check)
		{
			return Result.error(CodeMsg.REQUEST_ILLEGAL);
		}
		// 通过内存快速获取该商品是否秒杀结束
		Boolean over = localOverMap.get(itemId);
		// 如果秒杀已经结束
		if (over != null && over)  // ③
		{
			return Result.error(CodeMsg.MIAO_SHA_OVER);
		}
		// 预减库存
		long stock = fkRedisUtil.decr(ItemKey.miaoshaItemStock, "" + itemId);
		// 如果库存小于0，在内存中记录该商品秒杀结束，并返回秒杀结束的提示
		if (stock < 0)
		{
			localOverMap.put(itemId, true);
			return Result.error(CodeMsg.MIAO_SHA_OVER);
		}
		// 根据用户ID和商品ID获取秒杀订单
		MiaoshaOrder miaoshaOrder = miaoshaService
				.getMiaoshaOrderByUserIdAndItemId(user.getId(), itemId); // ④
		// 如果该用户已有对该商品的秒杀订单，判断为重复秒杀
		if (miaoshaOrder != null)
		{
			return Result.error(CodeMsg.REPEATE_MIAOSHA);
		}
		// 发送消息给RabbitMQ消息队列
		var miaoshaMessage = new MiaoshaMessage();
		miaoshaMessage.setUser(user);
		miaoshaMessage.setItemId(itemId);
		// 让秒杀消息进入队列
		mqSender.sendMiaoshaMessage(miaoshaMessage);  // ⑤
		return Result.success(0);
	}

	/**
	 * 获取订单状态
	 * 返回orderId：成功
	 * 返回-1：秒杀失败
	 * 返回0： 排队中
	 */
	@GetMapping(value = "/result")
	@ResponseBody
	@AccessLimit // 限制该方法必须登录才能访问
	public Result<Long> miaoshaResult(Model model, User user,
		@RequestParam("itemId") long itemId)
	{
		model.addAttribute("user", user);
		// 调用MiaoshaService的getMiaoshaResult()方法来获取秒杀结果
		long result = miaoshaService.getMiaoshaResult(user.getId(), itemId);
		return Result.success(result);
	}
}
