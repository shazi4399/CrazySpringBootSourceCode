package org.crazyit.app.controller;

import org.crazyit.app.exception.BookException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
public class BookController
{
	@GetMapping("/book/{id}")
	public String viewBook(@PathVariable Integer id)
	{
		if (id < 0)
		{
			throw new BookException("被查看图书的id必须大于0");
		}
		return "viewBook";
	}
}