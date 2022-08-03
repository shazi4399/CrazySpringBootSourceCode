package org.crazyit.app.controller;

import org.crazyit.app.redis.UserKey;
import org.crazyit.app.util.UUIDUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class CookieUtil
{
	// 工具方法，该方法将SessionID以Cookie形式写入浏览器
	public static void addSessionId(HttpServletResponse response, String token)
	{
		// 使用Cookie存放分布式Session的ID
		Cookie cookie = new Cookie(UserKey.COOKIE_NAME_TOKEN, token);
		cookie.setMaxAge(UserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	// 工具方法，用于读取指定Cookie的值
	public static String getCookieValue(HttpServletRequest request,
			String cookieName)
	{
		// 获取所有Cookie
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length <= 0)
		{
			return null;
		}
		// 遍历所有Cookie
		for (Cookie cookie : cookies)
		{
			// 找到并返回目标Cookie的值
			if (cookie.getName().equals(cookieName))
			{
				return cookie.getValue();
			}
		}
		return null;
	}
	// 工具方法，通过Cookie读取分布式Session的ID，如果不存在则创建它
	public static String getSessionId(HttpServletRequest request,
			HttpServletResponse response)
	{
		// 通过Cookie获取分布式SessionID
		String token = CookieUtil.getCookieValue(request,
				UserKey.COOKIE_NAME_TOKEN);
		// 如果SessionID为null，表明第一次访问该系统或Cookie已过期
		if (token == null)
		{
			// 生成随机字符串，该字符串将作为分布式SessionID
			token = UUIDUtil.uuid();
			// 将分布SessionID以Cookie写入浏览器
			addSessionId(response, token);
		}
		return token;
	}
}
