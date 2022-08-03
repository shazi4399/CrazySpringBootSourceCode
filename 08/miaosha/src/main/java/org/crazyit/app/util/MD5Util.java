package org.crazyit.app.util;

import org.apache.commons.codec.digest.DigestUtils;

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
public class MD5Util
{
	public static String md5(String src)
	{
		return DigestUtils.md5Hex(src);
	}

	public static String passToDbPass(String formPass, String randSalt)
	{
		String str = "" + randSalt.charAt(0) + randSalt.charAt(2)
				+ formPass + randSalt.charAt(5) + randSalt.charAt(4);
		return md5(str);
	}

	public static void main(String[] args)
	{
		// 加盐加密后的密码
		System.out.println(passToDbPass("123456", "0p9o8i"));
	}
}