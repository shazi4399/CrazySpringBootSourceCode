package org.crazyit.app.result;

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
public class CodeMsg
{
	private Integer code;
	private String msg;

	// 登录模块 5001xx
	public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器异常");
	public static final CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
	public static final CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500104, "访问太频繁！");

	// 登录模块 5002xx
	public static final CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
	public static final CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
	public static final CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
	public static final CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式错误");
	public static final CodeMsg MOBILE_NOT_EXIST = new CodeMsg(500214, "手机号不存在");
	public static final CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");

	// 订单模块 5004xx
	public static final CodeMsg ORDER_NOT_EXIST = new CodeMsg(500400, "订单不存在");

	// 秒杀模块 5005xx
	public static final CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
	public static final CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");
	public static final CodeMsg MIAOSHA_FAIL = new CodeMsg(500502, "秒杀失败");

	private CodeMsg(Integer code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode()
	{
		return code;
	}

	public void setCode(Integer code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
}
