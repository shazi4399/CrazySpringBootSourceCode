package org.crazyit.app.service;

import org.crazyit.app.domain.Item;

import java.util.Collection;

public interface ItemService
{
	Collection<Item> list();

	Item getItemById(Integer id);

	Item createOrUpdate(Item item);

	Item delete(Integer id);
}
