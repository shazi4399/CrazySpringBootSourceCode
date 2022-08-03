package org.crazyit.app.controller;

import java.io.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.crazyit.app.domain.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.jackson.*;

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
@JsonComponent
public class BookSerialize
{
	public static class Serializer extends JsonSerializer<Book>
	{
		@Override
		public void serialize(Book book, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException
		{
			System.out.println("序列化");
			// 输出对象开始的Token（也就是左花括号）
			jsonGenerator.writeStartObject();
			// 依次输出Book的4个属性
			jsonGenerator.writeNumberField("id", book.getId());
			// 对book的title属性，此处序列化为name
			jsonGenerator.writeObjectField("name", book.getTitle());
			jsonGenerator.writeObjectField("author", book.getAuthor());
			jsonGenerator.writeNumberField("price", book.getPrice());
			jsonGenerator.writeEndObject();
		}
	}

	public static class Deserializer extends JsonDeserializer<Book>
	{
		@Override
		public Book deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException
		{
			System.out.println("反序列化");
			var book = new Book();
			// 开始解析JSON字符串
			JsonToken jsonToken = jsonParser.getCurrentToken();
			String fieldName = null;
			// 如果还未解析到对象结束
			while (!jsonToken.equals(JsonToken.END_OBJECT))
			{
				if (!jsonToken.equals(JsonToken.FIELD_NAME))
				{
					jsonToken = jsonParser.nextToken();
					continue;
				}
				// 解析到field名
				fieldName = jsonParser.getCurrentName();
				// 解析解析下一个token（field名之后就是field值）
				jsonToken = jsonParser.nextToken();
				try
				{
					// 如果fieldName是name，则为field值的前后添加书名号
					if (fieldName.equals("name"))
					{
						String name = jsonParser.getText();
						if (!name.startsWith("《"))
						{
							name = "《" + name;
						}
						if (!name.endsWith("》"))
						{
							name = name + "》";
						}
						book.setTitle(name);
					}
					// 如果fieldName是price，则将价格打8折
					else if (fieldName.equals("price"))
					{
						book.setPrice(jsonParser.getDoubleValue() * 0.8);
					}
					// 对于其他fieldName，调用fieldName默认对应的setter方法
					else
					{
						BeanUtils.getPropertyDescriptor(Book.class, fieldName)
								.getWriteMethod().invoke(book, jsonParser.getText());
					}
					// 解析解析下一个Token
					jsonToken = jsonParser.nextToken();
				} catch (Exception e)
				{
					System.out.println("反序列化过程中出现异常：" + e);
				}
			}
			return book;
		}
	}
}