package org.crazyit.app.domain;

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
public class Item
{
	private Long id;
	private String itemName;
	private String title;
	private String itemImg;
	private String itemDetail;
	private Double itemPrice;
	private Integer stockNum;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getItemName()
	{
		return itemName;
	}

	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getItemImg()
	{
		return itemImg;
	}

	public void setItemImg(String itemImg)
	{
		this.itemImg = itemImg;
	}

	public String getItemDetail()
	{
		return itemDetail;
	}

	public void setItemDetail(String itemDetail)
	{
		this.itemDetail = itemDetail;
	}

	public Double getItemPrice()
	{
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice)
	{
		this.itemPrice = itemPrice;
	}

	public Integer getStockNum()
	{
		return stockNum;
	}

	public void setStockNum(Integer stockNum)
	{
		this.stockNum = stockNum;
	}

	@Override
	public String toString()
	{
		return "Item{" +
				"id=" + id +
				", itemName='" + itemName + '\'' +
				", title='" + title + '\'' +
				", itemImg='" + itemImg + '\'' +
				", itemDetail='" + itemDetail + '\'' +
				", itemPrice=" + itemPrice +
				", stockNum=" + stockNum +
				'}';
	}
}