package org.crazyit.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@RequestMapping("/book")
public class BookController
{
	// 添加注解，指定支持跨域资源共享
	@CrossOrigin(maxAge = 3600)
	@GetMapping("")
	public ResponseEntity<List<String>> books()
	{
		var books = List.of("疯狂Java讲义",
				"疯狂Python讲义",
				"轻量级Java Web企业应用实战",
				"疯狂Android讲义");
		return new ResponseEntity<>(books, HttpStatus.OK);
	}
}
