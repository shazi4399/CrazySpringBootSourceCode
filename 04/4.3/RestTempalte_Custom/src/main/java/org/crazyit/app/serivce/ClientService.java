package org.crazyit.app.serivce;

import org.crazyit.app.domain.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
public class ClientService implements CommandLineRunner
{
	@Override
	public void run(String... args)
	{
//		System.out.println(this.callCreate());

//		System.out.println(this.callList());

//		this.callUpdate();
//		System.out.println(this.callList());

//		System.out.println(this.callExechange());

		System.out.println(this.callExecute());
	}

	private final RestTemplate restTemplate;

	public ClientService(RestTemplateBuilder restTemplateBuilder)
	{
		// 设置它请求URL的根路径
		this.restTemplate = restTemplateBuilder.rootUri("http://192.168.1.188:8080/")
				.build();	// ①
	}

	public Book callCreate()
	{
		var book = new Book("疯狂Java讲义", 139.0, "李刚");
		return this.restTemplate.postForObject("/book", book, Book.class);
	}

	public void callUpdate()
	{
		var book = new Book("疯狂Android讲义", 129.0, "李刚");
		book.setId(1);
		this.restTemplate.put("/book", book);
	}

	@SuppressWarnings("unchecked")
	public List<Book> callList()
	{
		return this.restTemplate.getForObject("/book", List.class);
	}

	public Book callExechange()
	{
		var book = new Book("疯狂Python讲义", 128.0, "李刚");
		book.setId(1);
		// 创建HttpEntity作为请求参数
		var requestEntity = new HttpEntity<>(book);
		ResponseEntity<Book> resEntity = this.restTemplate
				.exchange("/book", HttpMethod.PUT, requestEntity, Book.class);
		System.out.println("服务器响应码:" + resEntity.getStatusCodeValue());
		return resEntity.getBody();
	}

	public String callExecute()
	{
		return this.restTemplate
			.execute("/book", HttpMethod.PUT, request -> {
				// 设置Accept请求头
				request.getHeaders().setAccept(List.of(MediaType.APPLICATION_JSON));
				// 设置Content-Type请求头
				request.getHeaders().set("Content-Type", "application/json");
				// 定义请求体的数据
				byte[] json = ("{\"id\":1, \"title\": \"疯狂Android讲义\", " +
						"\"price\": 129.0, \"author\":\"李刚\"}").getBytes(StandardCharsets.UTF_8);
				// 设置请求体
				request.getBody().write(json);
			}, response -> {
				System.out.println("code:" + response.getStatusCode());
				System.out.println("text:" + response.getStatusText());
				InputStream is = response.getBody();
				return new String(is.readAllBytes(), StandardCharsets.UTF_8);
			});
	}
}
