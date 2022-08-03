package org.crazyit.app.service;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.crazyit.app.dao.MiaoshaItemMapper;
import org.crazyit.app.dao.MiaoshaOrderMapper;
import org.crazyit.app.dao.OrderMapper;
import org.crazyit.app.domain.MiaoshaItem;
import org.crazyit.app.domain.MiaoshaOrder;
import org.crazyit.app.domain.Order;
import org.crazyit.app.domain.User;
import org.crazyit.app.redis.MiaoshaKey;
import org.crazyit.app.redis.FkRedisUtil;
import org.crazyit.app.redis.OrderKey;
import org.crazyit.app.util.MD5Util;
import org.crazyit.app.util.UUIDUtil;
import org.crazyit.app.util.VercodeUtil;

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
@Service
public class MiaoshaService
{
	private final FkRedisUtil fkRedisUtil;
	private final MiaoshaItemMapper miaoshaItemMapper;
	private final OrderMapper orderMapper;
	private final MiaoshaOrderMapper miaoshaOrderMapper;

	public MiaoshaService(FkRedisUtil fkRedisUtil,
			MiaoshaItemMapper miaoshaItemMapper,
			OrderMapper orderMapper,
			MiaoshaOrderMapper miaoshaOrderMapper)
	{
		this.fkRedisUtil = fkRedisUtil;
		this.miaoshaItemMapper = miaoshaItemMapper;
		this.orderMapper = orderMapper;
		this.miaoshaOrderMapper = miaoshaOrderMapper;
	}

	// 列出所有秒杀商品的方法
	public List<MiaoshaItem> listMiaoshaItem()
	{
		return miaoshaItemMapper.findAll();
	}

	// 根据商品ID获取秒杀商品的方法
	public MiaoshaItem getMiaoshaItemById(long itemId)
	{
		return miaoshaItemMapper.findById(itemId);
	}

	// 执行秒杀的方法
	@Transactional
	public Order miaosha(User user, MiaoshaItem item)
	{
		// 将秒杀商品的库存减1
		boolean success = reduceStock(item);
		if (success)
		{
			// 创建普通订单和秒杀订单
			return createOrder(user, item);
		}
		else
		{
			// 如果秒杀失败，将该商品的秒杀状态设为已结束
			fkRedisUtil.set(MiaoshaKey.isItemOver,
					"" + item.getId(), true);
			return null;
		}
	}

	// 将秒杀商品的库存减1
	public boolean reduceStock(MiaoshaItem miaoshaItem)
	{
		int ret = miaoshaItemMapper.reduceStock(miaoshaItem);
		return ret > 0;
	}

	// 根据用户id和物品id返回秒杀订单id，
	// 如果没有秒杀成功，当秒杀结束时返回-1，秒杀未结束时返回0
	public long getMiaoshaResult(Long userId, long itemId)
	{
		// 根据用户ID和商品ID获取秒杀订单
		MiaoshaOrder order = getMiaoshaOrderByUserIdAndItemId(userId, itemId);
		// 如果秒杀订单不为null，返回订单ID
		if (order != null)
		{
			return order.getOrderId();
		}
		else
		{
			// 根据物品ID获取该商品的秒杀状态
			boolean isOver = fkRedisUtil
					.exists(MiaoshaKey.isItemOver, "" + itemId);
			// 如果秒杀已经结束返回-1
			if (isOver)
			{
				return -1;
			}
			// 否则返回0
			else
			{
				return 0;
			}
		}
	}

	// 判断用户输入的秒杀地址是否正确
	public boolean checkPath(User user, long itemId, String path)
	{
		if (user == null || path == null)
		{
			return false;
		}
		// 获取Redis缓存的UUID字符串
		String pathOld = fkRedisUtil.get(MiaoshaKey.miaoshaPath, ""
				+ user.getId() + "_" + itemId, String.class);
		// 拿用户输入的UUID字符串与Redis缓存的UUID字符串进行比较
		return path.equals(pathOld);
	}

	// 生成秒杀地址的方法
	public String createMiaoshaPath(User user, long itemId)
	{
		if (user == null || itemId <= 0)
		{
			return null;
		}
		// 先生成UUID字符串，对UUID字符串进行MD5加密
		String str = MD5Util.md5(UUIDUtil.uuid());
		// 将动态生成的秒杀地址存入Redis中
		fkRedisUtil.set(MiaoshaKey.miaoshaPath, ""
				+ user.getId() + "_" + itemId, str);
		return str;
	}

	// 生成秒杀图形验证码
	public BufferedImage createVerifyCode(User user, long itemId)
	{
		if (user == null || itemId <= 0)
		{
			return null;
		}
		Random rdm = new Random();
		String verifyCode = VercodeUtil.generateVerifyCode(rdm);
		int rnd = VercodeUtil.calc(verifyCode);
		// 将验证码的值存到Redis中
		fkRedisUtil.set(MiaoshaKey.miaoshaVerifyCode,
				user.getId() + "," + itemId, rnd);
		// 返回生成的图片
		return VercodeUtil.createVerifyImage(verifyCode, rdm);
	}

	// 检查用户输入的秒杀验证码是否正确
	public boolean checkVerifyCode(User user, long itemId, int verifyCode)
	{
		if (user == null || itemId <= 0)
		{
			return false;
		}
		// 获取Redis中保存的验证码
		Integer codeOld = fkRedisUtil.get(MiaoshaKey.miaoshaVerifyCode,
			user.getId() + "," + itemId, Integer.class);
		// 拿用户输入的验证码与Redis中保存的验证码进行比较
		if (codeOld == null || codeOld - verifyCode != 0)
		{
			return false;
		}
		// 删除Redis中保存的验证码
		fkRedisUtil.delete(MiaoshaKey.miaoshaVerifyCode,
			user.getId() + "," + itemId);
		return true;
	}

	// 根据用户ID和商品ID获取秒杀订单
	public MiaoshaOrder getMiaoshaOrderByUserIdAndItemId(long userId, long itemId)
	{
		// 从Redis缓存读取订单
		return fkRedisUtil.get(OrderKey.miaoshaOrderByUserIdAndItemId,
				"" + userId + "_" + itemId, MiaoshaOrder.class);
	}

	// 创建普通订单和秒杀订单
	@Transactional
	public Order createOrder(User user, MiaoshaItem item)
	{
		// 创建普通订单
		var order = new Order();
		// 设置订单信息
		order.setUserId(user.getId());
		order.setCreateDate(new Date());
		order.setOrderNum(1);
		order.setItemId(item.getItemId());
		order.setItemName(item.getItemName());
		order.setOrderPrice(item.getMiaoshaPrice());
		order.setOrderChannel(1);
		// 设置订单状态，0代表未支付订单
		order.setStatus(0);
		// 保存普通订单
		orderMapper.save(order);
		// 创建秒杀订单
		var miaoshaOrder = new MiaoshaOrder();
		// 设置秒杀订单信息
		miaoshaOrder.setUserId(user.getId());
		miaoshaOrder.setItemId(item.getItemId());
		miaoshaOrder.setOrderId(order.getId());
		// 保存秒杀订单
		miaoshaOrderMapper.save(miaoshaOrder);
		// 将秒杀订单保存到Redis缓存中
		fkRedisUtil.set(OrderKey.miaoshaOrderByUserIdAndItemId,
				"" + user.getId() + "_" + item.getItemId(), miaoshaOrder);
		return order;
	}

	// 根据订单ID和用户ID获取订单的方法
	public Order getOrderByIdAndOwnerId(long orderId, long userId)
	{
		return orderMapper.findByIdAndOwnerId(orderId, userId);
	}
}
