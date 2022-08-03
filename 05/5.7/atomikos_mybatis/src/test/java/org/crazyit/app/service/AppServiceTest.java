package org.crazyit.app.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.TransactionManager;

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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class AppServiceTest
{
	@Autowired
	private AppService appService;
	@Autowired // 获取Spring容器
	private ApplicationContext ctx;

	@Test
	public void testSaveUserAndNews()
	{
		appService.saveUserAndNews();
	}
	@Test
	public void testUpdateUserAndNews()
	{
		System.out.println("==" + ctx.getBeansOfType(TransactionManager.class));
		appService.updateUserAndNews();
	}
}
