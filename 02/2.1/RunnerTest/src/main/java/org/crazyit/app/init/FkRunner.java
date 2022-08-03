package org.crazyit.app.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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
@Component
public class FkRunner implements ApplicationRunner
{
	// 该run()方法将应用启动完成之前执行
	@Override
	public void run(ApplicationArguments args)
	{
		System.out.println("模拟对SpringApplication执行初始化，下面获取运行参数");
		System.out.println("getSourceArgs:" + Arrays.toString(args.getSourceArgs()));
		System.out.println("getOptionValues:" + args.getOptionValues("book.name"));
		System.out.println("getNonOptionArgs:" + args.getNonOptionArgs());
	}
}
