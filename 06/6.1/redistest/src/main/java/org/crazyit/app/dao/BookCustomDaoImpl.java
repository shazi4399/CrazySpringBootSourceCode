package org.crazyit.app.dao;

import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class BookCustomDaoImpl implements BookCustomDao
{
	@Autowired
	private StringRedisTemplate redisTemplate;

	@Override
	public void hmset(String key, Map<String, String> hash)
	{
		// 调用opsForHash()获取操作Hash对象的HashOperations，再调用putAll()方法
		redisTemplate.opsForHash().putAll(key, hash);
	}

	@Override
	public List<Book> customQuery(double startPrice)
	{
		// 调用execute(RedisCallback)执行自定义操作
		return redisTemplate.execute((RedisCallback<List<Book>>) connection -> {
			List<Book> result = new ArrayList<>();
			StringRedisConnection conn = (StringRedisConnection) connection;
			// 查询key为book对应的Set，该Set保存了所有Book对象的id
			Set<String> ids = conn.sMembers("book");
			// 遍历所有Book对象的id
			for (String idStr : ids)
			{
				// 实际Book对象的key遵守格式："book:id"
				String objKey = "book:" + idStr;
				// 读取实际对象映射的Hash对象
				Map<String, String> data = conn.hGetAll(objKey);
				String priceStr = data.get("price");
				if (priceStr != null)
				{
					var price = Double.parseDouble(priceStr);
					if (price > startPrice)
					{
						Integer id = Integer.parseInt(idStr);
						// 读取数据，并转换为了Book
						String name = data.get("name");
						String description = data.get("description");
						// 将读取的数据封装成Book对象
						var b = new Book(name, description, price);
						b.setId(id);
						result.add(b);
					}
				}
			}
			return result;
		});
	}
}
