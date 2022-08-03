package org.crazyit.app.domain;

import java.util.Date;

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
public class MiaoshaItem extends Item
{
	private Long id;
	private Long itemId;
	private double miaoshaPrice;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getItemId()
	{
		return itemId;
	}

	public void setItemId(Long itemId)
	{
		this.itemId = itemId;
	}

	public double getMiaoshaPrice()
	{
		return miaoshaPrice;
	}

	public void setMiaoshaPrice(double miaoshaPrice)
	{
		this.miaoshaPrice = miaoshaPrice;
	}

	public Integer getStockCount()
	{
		return stockCount;
	}

	public void setStockCount(Integer stockCount)
	{
		this.stockCount = stockCount;
	}

	public Date getStartDate()
	{
		return startDate;
	}

	public void setStartDate(Date startDate)
	{
		this.startDate = startDate;
	}

	public Date getEndDate()
	{
		return endDate;
	}

	public void setEndDate(Date endDate)
	{
		this.endDate = endDate;
	}

	@Override
	public String toString()
	{
		return "MiaoshaItem{" +
				"id=" + id +
				", itemId=" + itemId +
				", miaoshaPrice=" + miaoshaPrice +
				", stockCount=" + stockCount +
				", startDate=" + startDate +
				", endDate=" + endDate +
				'}';
	}
}
