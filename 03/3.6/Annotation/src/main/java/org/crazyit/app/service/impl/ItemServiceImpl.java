package org.crazyit.app.service.impl;

import org.crazyit.app.domain.Item;
import org.crazyit.app.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@Service
public class ItemServiceImpl implements ItemService
{
	private final Map<Integer, Item> data = new ConcurrentHashMap<>();
	private static final AtomicInteger idGenerator = new AtomicInteger(0);

	@Override
	public Collection<Item> list()
	{
		return this.data.values();
	}

	@Override
	public Item getItemById(Integer id)
	{
		return this.data.get(id);
	}

	@Override
	public Item createOrUpdate(Item item)
	{
		// 修改物品
		if (item.getId() != null && data.containsKey(item.getId()))
		{
			this.data.put(item.getId(), item);
		}
		else
		{
			Integer id = idGenerator.incrementAndGet();
			item.setId(id);
			this.data.put(id, item);
		}
		return item;
	}
	@Override
	public Item delete(Integer id)
	{
		return this.data.remove(id);
	}
}
