package org.crazyit.app.dao;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

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
	private ReactiveMongoTemplate mongoTemplate;

	@Override
	public Flux<Book> customQuery1(String regex, double startPrice)
	{
		// 要求name匹配正则表达式且price大于指定值
		Query query = Query.query(Criteria.where("name").regex(regex)
				.and("price").gt(startPrice));
		// 调用find()方法执行查询
		return mongoTemplate.find(query, Book.class);
	}

	@Override
	public Flux<Book> customQuery2(double startPrice, double endPrice)
	{
		// 调用execute()方法执行自定义查询
		return mongoTemplate.execute(Book.class,
				collection -> {
					// 设置条件，实际就是得到{ $gte: startPrice, $lte: endPrice}
					var cond = new BasicDBObject();
					cond.put("$gte", startPrice);
					cond.put("$lte", endPrice);
					// 设置查询条件，实际上得到{price: cond}
					var query = new BasicDBObject("price", cond);
					// 调用MongoCollection的find()方法执行查询
					// 再将查询结果转换成Flux
					// map()方法则用于将Flux中的元素转换成另一中类型
					return Flux.from(collection.find(query)).map(doc -> {
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
						return b;
					});
				});
	}
}