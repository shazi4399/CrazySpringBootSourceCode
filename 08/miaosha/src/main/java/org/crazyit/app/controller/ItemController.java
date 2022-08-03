package org.crazyit.app.controller;

import org.apache.commons.lang3.StringUtils;
import org.crazyit.app.access.AccessLimit;
import org.crazyit.app.domain.MiaoshaItem;
import org.crazyit.app.domain.User;
import org.crazyit.app.redis.FkRedisUtil;
import org.crazyit.app.redis.ItemKey;
import org.crazyit.app.result.Result;
import org.crazyit.app.service.MiaoshaService;
import org.crazyit.app.vo.ItemDetailVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/item")
public class ItemController
{
	private final MiaoshaService miaoshaService;
	private final FkRedisUtil fkRedisUtil;
	// 定义ThymeleafViewResolver用于解析Thymeleaf页面模板
	private final ThymeleafViewResolver thymeleafViewResolver;
	public ItemController(MiaoshaService miaoshaService, FkRedisUtil fkRedisUtil,
			ThymeleafViewResolver thymeleafViewResolver)
	{
		this.miaoshaService = miaoshaService;
		this.fkRedisUtil = fkRedisUtil;
		this.thymeleafViewResolver = thymeleafViewResolver;
	}

	@GetMapping("/list")
	@ResponseBody
	@AccessLimit // 限制该方法必须登录才能访问
	public String list(HttpServletRequest request,
			HttpServletResponse response, User user)
	{
		// 从Redis缓存中取数据
		String html = fkRedisUtil.get(ItemKey.itemList, "", String.class);
		// 如果缓存中有HTML页面，直接返回HTML页面
		if (!StringUtils.isEmpty(html))
		{
			return html;
		}
		// 如果缓存中没有HTML页面才会去执行查询
		// 查询秒杀商品列表
		List<MiaoshaItem> itemList = miaoshaService.listMiaoshaItem(); // ①
		IWebContext ctx = new WebContext(request, response,
				request.getServletContext(), request.getLocale(),
				Map.of("user", user, "itemList", itemList));
		// 渲染静态的HTML内容
		html = thymeleafViewResolver.getTemplateEngine().process("item_list", ctx);
		// 将静态HTML内容存入缓存
		if (!StringUtils.isEmpty(html))
		{
			fkRedisUtil.set(ItemKey.itemList, "", html); // ②
		}
		return html;
	}

	@GetMapping(value = "/detail/{itemId}")
	@ResponseBody
	@AccessLimit // 限制该方法必须登录才能访问
	public Result<ItemDetailVo> detail(User user,
			@PathVariable("itemId") long itemId)
	{
		MiaoshaItem item = miaoshaService.getMiaoshaItemById(itemId);
		// 获取秒杀开始时间
		long startAt = item.getStartDate().getTime();
		// 获取秒杀的结束时间
		long endAt = item.getEndDate().getTime();
		long now = System.currentTimeMillis();
		// 定义距离开始秒杀还有多久的变量
		int remainSeconds;
		if (now < startAt)
		{
			// 秒杀还没开始
			remainSeconds = (int) ((startAt - now) / 1000);
		}
		else if (now > endAt)
		{
			// 秒杀已结束
			remainSeconds = -1;
		}
		else
		{
			// 秒杀进行中
			remainSeconds = 0;
		}
		// 定义秒杀还剩多久结束的变量
		var leftSeconds = (int) ((endAt - now ) / 1000);
		// 创建ItemDetailVo，用于封装秒杀商品详情
		ItemDetailVo itemDetailVo = new ItemDetailVo();
		itemDetailVo.setMiaoshaItem(item);
		itemDetailVo.setUser(user);
		itemDetailVo.setRemainSeconds(remainSeconds);
		itemDetailVo.setLeftSeconds(leftSeconds);
		return Result.success(itemDetailVo);
	}
}