package org.crazyit.app.util;

import org.crazyit.app.domain.User;
import org.crazyit.app.redis.MiaoshaKey;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
public class VercodeUtil
{
	private static final char[] ops = new char[]{'+', '-', '*'};
	// 生成图形验证码的表达式
	public static String generateVerifyCode(Random rdm)
	{
		// 生成四个随机整数
		int num1 = rdm.nextInt(10) + 1;
		int num2 = rdm.nextInt(10) + 1;
		int num3 = rdm.nextInt(10) + 1;
		int num4 = rdm.nextInt(10) + 1;
		var opsLen = ops.length;
		// 生成三个随机的运算符
		char op1 = ops[rdm.nextInt(opsLen)];
		char op2 = ops[rdm.nextInt(opsLen)];
		char op3 = ops[rdm.nextInt(opsLen)];
		// 将整数和运算符拼接成表达式
		return "" + num1 + op1 + num2 + op2 + num3 + op3 + num4;
	}
	// 根据图形验证码表达式来生成验证码图片
	public static BufferedImage createVerifyImage(String verifyCode, Random rdm)
	{
		var width = 120;
		var height = 32;
		// 创建图形
		var image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		// 设置背景色
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// 绘制边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// 生成一些干扰椭圆
		for (int i = 0; i < 50; i++)
		{
			int x = rdm.nextInt(width);
			int y = rdm.nextInt(height);
			g.drawOval(x, y, 0, 0);
		}
		// 设置颜色
		g.setColor(new Color(0, 100, 0));
		// 设置字体
		g.setFont(new Font("Candara", Font.BOLD, 24));
		// 绘制图形验证码
		g.drawString(verifyCode, 8, 24);
		g.dispose();
		// 返回图片
		return image;
	}
	public static int calc(String exp)
	{
		try
		{
			// 获取脚本引擎，用于计算表达式的值
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("JavaScript");
			// 计算表达式的值
			return (Integer) engine.eval(exp);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
