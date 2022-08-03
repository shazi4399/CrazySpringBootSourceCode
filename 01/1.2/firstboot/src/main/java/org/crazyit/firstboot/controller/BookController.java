package org.crazyit.firstboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
public class BookController
{
	@GetMapping("/")
	public String index(Model model)
	{
		model.addAttribute("tip", "欢迎访问第一个Spring Boot应用");
		return "hello";
	}
	@GetMapping("/rest")
	@ResponseBody
	public ResponseEntity restIndex()
	{
		return new ResponseEntity<>("欢迎访问第一个Spring Boot应用",
				null, HttpStatus.OK);
	}
}
