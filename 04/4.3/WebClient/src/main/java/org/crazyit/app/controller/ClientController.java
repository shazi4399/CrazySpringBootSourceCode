package org.crazyit.app.controller;

import org.crazyit.app.domain.Book;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
@RestController
public class ClientController
{
	private final WebClient webClient;

	public ClientController(WebClient.Builder webClientBuilder)
	{
		// 使用WebClient.Builder创建WebClient
		this.webClient = webClientBuilder.baseUrl("http://192.168.1.188:8080/")
				.build();	// ①
	}

	@GetMapping("/callCreate")
	public Mono<Book> callCreate()
	{
		var book = new Book("疯狂Java讲义", 139.0, "李刚");
		return this.webClient
				// 设置发送POST请求
				.post().uri("/book")
				// 设置自定义的请求头
				.header("Content-Type", "application/json")
				// 设置Accept请求头
				.accept(MediaType.APPLICATION_JSON)
				// 设置请求体数据
				.body(Mono.just(book), Book.class)
				// 发送请求，与服务器端交互
				.retrieve().bodyToMono(Book.class);
	}

	@GetMapping("/callList")
	public Flux<Book> callList()
	{
		return this.webClient
				// 设置发送GET请求
				.get().uri("/book")
				// 设置自定义的请求头
				.header("Content-Type", "application/json")
				// 设置Accept请求头
				.accept(MediaType.APPLICATION_JSON)
				// 发送请求，与服务器端交互
				.retrieve().bodyToFlux(Book.class);
	}

	@GetMapping("/callUpdate")
	public Mono<Book> callUpdate()
	{
		var book = new Book("疯狂Android讲义", 128.0, "李刚");
		book.setId(1);
		return this.webClient
				// 设置发送PUT请求
				.put().uri("/book")
				// 设置自定义的请求头
				.header("Content-Type", "application/json")
				// 设置Accept请求头
				.accept(MediaType.APPLICATION_JSON)
				// 设置请求体数据
				.body(Mono.just(book), Book.class)
				// 发送请求，与服务器端交互
				.retrieve().bodyToMono(Book.class);
	}

	@GetMapping("/callMethod")
	public Mono<Book> callMethod()
	{
		var book = new Book("疯狂Python讲义", 129.0, "李刚");
		book.setId(1);
		return this.webClient
				// 通过method()方法可指定发送任意方式的请求
				.method(HttpMethod.PUT)
				.uri("/book")
				.header("Content-Type", "application/json")
				.accept(MediaType.APPLICATION_JSON)
				// 设置请求体数据
				.body(Mono.just(book), Book.class)
				// 发送请求，与服务器端交互
				.retrieve().bodyToMono(Book.class);
	}
}