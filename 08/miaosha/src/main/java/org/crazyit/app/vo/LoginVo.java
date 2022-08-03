package org.crazyit.app.vo;

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
public class LoginVo
{
	private String mobile;
	private String password;
	private Integer vercode;

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Integer getVercode()
	{
		return vercode;
	}

	public void setVercode(Integer vercode)
	{
		this.vercode = vercode;
	}

	@Override
	public String toString()
	{
		return "LoginVo{" +
				"mobile='" + mobile + '\'' +
				", password='" + password + '\'' +
				", vercode='" + vercode + '\'' +
				'}';
	}
}