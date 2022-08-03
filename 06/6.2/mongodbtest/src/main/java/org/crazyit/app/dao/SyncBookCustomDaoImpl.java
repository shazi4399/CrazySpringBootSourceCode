package org.crazyit.app.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

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
public class SyncBookCustomDaoImpl implements SyncBookCustomDao
{
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Book> customQuery1(String regex, double startPrice)
	{
		// 要求name匹配正则表达式且price大于指定值
		Query query = Query.query(Criteria.where("name").regex(regex)
				.and("price").gt(startPrice));
		// 执行查询
		return mongoTemplate.find(query, Book.class);
	}

	@Override
	public List<Book> customQuery2(double startPrice, double endPrice)
	{
		return mongoTemplate.execute(Book.class,
				collection -> {
					// 设置条件，实际就是得到{ $gte: startPrice, $lte: endPrice}
					var cond = new BasicDBObject();
					cond.put("$gte", startPrice);
					cond.put("$lte", endPrice);
					// 设置查询条件
					var query = new BasicDBObject("price", cond);
					MongoCursor<Document> cursor = collection.find(query).iterator();
					List<Book> result = new ArrayList<>();
					while (cursor.hasNext())
					{
						Document doc = cursor.next();
						// 将Document转换为Book
						var b = new Book();
						Object id = doc.get("_id");
						if (id instanceof String)
						{
							b.setId((String) id);
						}
						else
						{
							b.setId(((ObjectId) id).toHexString());
						}
						b.setName((String) doc.get("name"));
						b.setDescription((String) doc.get("description"));
						b.setPrice((Double) doc.get("price"));
						result.add(b);
					}
					return result;
				});
	}
}