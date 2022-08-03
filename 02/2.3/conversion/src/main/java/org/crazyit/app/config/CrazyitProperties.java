package org.crazyit.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import java.net.InetAddress;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
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
@ConfigurationProperties(prefix = "org.crazyit")
@Component
public class CrazyitProperties
{
	private Duration timeout;
	@DurationUnit(ChronoUnit.SECONDS)
	private Duration lastTime;
	private Period runPeriod;
	@DataSizeUnit(DataUnit.MEGABYTES)
	private DataSize maxSize;

	public Duration getTimeout()
	{
		return timeout;
	}

	public void setTimeout(Duration timeout)
	{
		this.timeout = timeout;
	}

	public Duration getLastTime()
	{
		return lastTime;
	}

	public void setLastTime(Duration lastTime)
	{
		this.lastTime = lastTime;
	}

	public Period getRunPeriod()
	{
		return runPeriod;
	}

	public void setRunPeriod(Period runPeriod)
	{
		this.runPeriod = runPeriod;
	}

	public DataSize getMaxSize()
	{
		return maxSize;
	}

	public void setMaxSize(DataSize maxSize)
	{
		this.maxSize = maxSize;
	}
}
