package org.crazyit.app.controller;

import org.crazyit.app.service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class HelloController
{
	private final MessageService messService;

	public HelloController(MessageService messService)
	{
		this.messService = messService;
	}

	@GetMapping("/produce/{key}/{message}")
	public String produce(@PathVariable String message,
			@PathVariable(required = false) String key)
	{
		messService.produce(key, message);
		return "发送消息";
	}
	@GetMapping("/produce/{message}")
	public String produce(@PathVariable String message)
	{
		messService.produce(null, message);
		return "发送消息";
	}
}
