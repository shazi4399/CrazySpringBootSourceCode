package org.crazyit.app.config;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
// 指定读取以org.crazyit.*开头的属性
@ConfigurationProperties(prefix = "org.crazyit", ignoreUnknownFields=false)
@Component
@Validated
public class CrazyitProperties
{
	@NotEmpty
	private String name;
	@Range(max = 150, min=90, message = "价格必须位于90～150之间")
	private double price;
	@Pattern(regexp = "[1][3-8][0-9]{9}", message = "必须输入有效的手机号")
	private String mobile;
	@Valid
	private final Item item = new Item();

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public Item getItem()
	{
		return item;
	}

	public static class Item
	{
		@Length(min=5, max=10, message = "品牌名长度必须在5到10个字符")
		private String brand;
		@Size(min = 1, message = "comments至少包含一个元素")
		private List<String> comments = new ArrayList<>(Collections.singleton("GREAT"));

		public String getBrand()
		{
			return brand;
		}

		public void setBrand(String brand)
		{
			this.brand = brand;
		}

		public List<String> getComments()
		{
			return comments;
		}

		public void setComments(List<String> comments)
		{
			this.comments = comments;
		}
	}
}
