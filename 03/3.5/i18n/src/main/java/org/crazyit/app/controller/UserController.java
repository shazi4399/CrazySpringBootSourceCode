package org.crazyit.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.web.context.request.WebRequest;

import org.crazyit.app.service.UserService;
import org.crazyit.app.domain.User;

import java.util.Locale;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@Controller
public class UserController
{
	// 依赖注入userService组件
	@Autowired
	private UserService userService;
	@Autowired
	private MessageSource messageSource;
	@PostMapping("/login")
	public String login(User user, Model model,
			WebRequest webRequest, Locale locale)
	{
		if (userService.userLogin(user) > 0)
		{
			model.addAttribute("tip", messageSource.getMessage(
					"success_info", new String[]{user.getUsername()}, locale));
			// 为session添加属性
			webRequest.setAttribute("userName", user.getUsername(),
					WebRequest.SCOPE_SESSION);
			return "success";
		}
		model.addAttribute("tip", messageSource.getMessage("error_info",
				null, locale));
		return "fail";
	}
}
