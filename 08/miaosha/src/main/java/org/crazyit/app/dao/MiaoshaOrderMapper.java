package org.crazyit.app.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.crazyit.app.domain.MiaoshaOrder;


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
@Mapper
public interface MiaoshaOrderMapper
{
	// 根据用户ID和商品ID获取秒杀订单
	@Select("select miaosha_order_id as id, user_id as userId, order_id as " +
			"orderId, item_id as itemId from miaosha_order " +
			"where user_id=#{userId} and item_id=#{itemId}")
	MiaoshaOrder findByUserIdItemId(@Param("userId") long userId,
			@Param("itemId") long itemId);
	// 插入秒杀订单
	@Insert("insert into miaosha_order(user_id, item_id, order_id) values " +
			"(#{userId}, #{itemId}, #{orderId})")
	int save(MiaoshaOrder miaoshaOrder);
}