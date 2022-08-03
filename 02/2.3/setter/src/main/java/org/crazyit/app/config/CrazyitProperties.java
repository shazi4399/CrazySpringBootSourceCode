package org.crazyit.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
public class CrazyitProperties
{
	private boolean enabled;
	private String name;
	private InetAddress remoteAddress;
	private final Item item = new Item();

	public boolean isEnabled()
	{
		return this.enabled;
	}
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public InetAddress getRemoteAddress()
	{
		return remoteAddress;
	}
	public void setRemoteAddress(InetAddress remoteAddress)
	{
		this.remoteAddress = remoteAddress;
	}

	public Item getItem()
	{
		return item;
	}

	public static class Item
	{
		private String brand;
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
