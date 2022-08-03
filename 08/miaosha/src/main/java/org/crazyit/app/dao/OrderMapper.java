package org.crazyit.app.dao;

import org.apache.ibatis.annotations.*;
import org.crazyit.app.domain.Order;

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
public interface OrderMapper
{
	// 向order_inf表插入新的记录
	@Insert("insert into order_inf(user_id, item_id, item_name, order_num, " +
			"order_price, order_channel, order_status, create_date) values" +
			"(#{userId}, #{itemId}, #{itemName}, #{orderNum}, #{orderPrice}, " +
			"#{orderChannel}, #{status}, #{createDate})")
	// 指定获取向order_inf插入记录时所获取的自增长主键值
	@Options(useGeneratedKeys = true, keyProperty = "id")
	long save(Order order);

	// 根据订单ID和下单用户的ID来获取订单
	@Select("select order_id as id, user_id as userId, item_id as itemId, " +
			"item_name as itemName, order_num as orderNum, order_price as " +
			"orderPrice, order_channel as orderChannel, order_status as " +
			"status, create_date as createDate, pay_date as payDate from " +
			"order_inf where order_id = #{param1} and user_id = #{param2}")
	Order findByIdAndOwnerId(long orderId, long userId);
}