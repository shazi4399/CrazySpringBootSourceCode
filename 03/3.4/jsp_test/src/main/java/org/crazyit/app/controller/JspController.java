package org.crazyit.app.controller;

import org.crazyit.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

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
@Controller
public class JspController
{
	@GetMapping("/")
	public String index()
	{
		return "index";
	}

	@PostMapping("/login")
	public String login(String username, String pass, Model model, WebRequest webReq)
	{
		if (username.equals("crazyit.org") && pass.equals("leegang")) {
			webReq.setAttribute("name", username, WebRequest.SCOPE_SESSION);
			webReq.setAttribute("role", "manager", WebRequest.SCOPE_SESSION);
			return "main";
		}
		model.addAttribute("error", "用户名/密码不匹配");
		return "index";
	}

	@Autowired
	private BookService bookService;

	@GetMapping("/viewBooks")
	public void viewBooks(Model model)
	{
		model.addAttribute("bookList", bookService.getAllBooks());
	}
}
