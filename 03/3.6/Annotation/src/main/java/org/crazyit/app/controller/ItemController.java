package org.crazyit.app.controller;

import org.crazyit.app.domain.Item;
import org.crazyit.app.exception.ItemNotFoundException;
import org.crazyit.app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Objects;

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
@RequestMapping("/item")
public class ItemController
{
	@GetMapping("/hello")
	public Mono<String> hello()
	{
		return Mono.just("Hello WebFlux");
	}

	@Autowired
	private ItemService itemService;

	@GetMapping("/{id}")
	public Mono<Item> getByItemId(@PathVariable("id") Integer id)
	{
		return Mono.justOrEmpty(this.itemService.getItemById(id))
				.switchIfEmpty(Mono.error(new ItemNotFoundException("商品找不到")));
	}

	@PostMapping("")
	public Mono<Item> create(@RequestBody Item item)
	{
		return Mono.just(this.itemService.createOrUpdate(item));
	}

	@PutMapping("")
	public Mono<Item> update(@RequestBody Item item)
	{
		Objects.requireNonNull(item);
		return Mono.just(this.itemService.createOrUpdate(item));
	}

	@DeleteMapping("/{id}")
	public Mono<Item> delete(@PathVariable("id") Integer id)
	{
		return Mono.justOrEmpty(this.itemService.delete(id));
	}

	@GetMapping("")
	public Flux<Item> list(Integer size)
	{
		if (size == null || size == 0)
		{
			size = 5;
		}
		return Flux.fromIterable(this.itemService.list()).take(size);
	}

	@GetMapping(value = "", produces = "application/stream+json")
	public Flux<Item> list()
	{
		// 需要周期生成数据，使用 Flux.interval
		return Flux.interval(Duration.ofMillis(2000))
				.onBackpressureDrop()
				// 每隔interval,执行一次itemService.list()的方法
				.map((interval) -> itemService.list())
				// 将List<Item>转换成Flux<Item>
				.flatMapIterable(item -> item)
				.log("生成信息");
	}
}
