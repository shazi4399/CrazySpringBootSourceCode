package org.crazyit.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@GetMapping
	public Map<String, Object> hello()
	{
		logger.trace("-------TRACE级别的日志-------");
		logger.debug("-------DEBUG级别的日志-------");
		logger.info("-------INFO级别的日志-------");
		logger.warn("-------WARN级别的日志-------");
		logger.error("-------ERROR级别的日志-------");
		return Map.of("hello", "Hello");
	}
}
