package org.crazyit.funny.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

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
public class WriterTemplate
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	private final DataSource dataSource;
	private Connection conn;
	private final File dest;
	private final Charset charset;
	private RandomAccessFile raf;

	public WriterTemplate(DataSource dataSource) throws SQLException
	{
		this.dataSource = dataSource;
		this.dest = null;
		this.charset = null;
		if (Objects.nonNull(this.dataSource))
		{
			log.debug("==========获取数据库连接==========");
			this.conn = dataSource.getConnection();
		}
	}

	public WriterTemplate(File dest, Charset charset) throws FileNotFoundException
	{
		this.dest = dest;
		this.charset = charset;
		this.dataSource = null;
		this.raf = new RandomAccessFile(this.dest, "rw");
	}

	public void write(String message) throws IOException, SQLException
	{
		if (Objects.nonNull(this.conn))
		{
			// 查询当前数据库的funny_message表是否存在
			ResultSet rs = conn.getMetaData().getTables(conn.getCatalog(), null,
					"funny_message", null);
			//  如果funny_message表不存在
			if (!rs.next())
			{
				log.debug("~~~~~~创建funny_message表~~~~~~");
				conn.createStatement().execute("create table funny_message " +
						"(id int primary key auto_increment, message_text text)");
				rs.close();
			}
			log.debug("~~~~~~输出到数据表~~~~~~");
			// 插入要输出的字符串
			conn.createStatement().executeUpdate("insert into " +
					"funny_message values (null, '" + message + "')");
		}
		else
		{
			log.debug("~~~~~~输出到文件~~~~~~");
			// 输出到文件
			raf.seek(this.dest.length());
			raf.write((message + "\n").getBytes(this.charset));
		}
	}
	// 关闭资源
	public void close() throws SQLException, IOException
	{
		if (this.conn != null)
		{
			this.conn.close();
		}
		if (this.raf != null)
		{
			this.raf.close();
		}
	}
}
