package org.crazyit.app.web;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
public class FirstServlet extends HttpServlet
{
	@Value("${crazyit.greeting}")
	private String greeting;
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		log("----FirstServlet---");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.println("为Spring Boot添加的第一个Servlet，信息：" + greeting);
	}
}
