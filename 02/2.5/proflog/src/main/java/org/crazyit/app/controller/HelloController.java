package org.crazyit.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
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
@Slf4j
public class HelloController
{
	private final DataSource dataSource;
	@Autowired
	public HelloController(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	@GetMapping
	public Map<String, String> hello() throws SQLException
	{
		log.trace("-------TRACE级别的日志-------");
		log.debug("-------DEBUG级别的日志-------");
		log.info("-------INFO级别的日志-------");
		log.warn("-------WARN级别的日志-------");
		log.error("-------ERROR级别的日志-------");
		return Map.of("数据库",
				dataSource.getConnection().getCatalog());
	}
}
