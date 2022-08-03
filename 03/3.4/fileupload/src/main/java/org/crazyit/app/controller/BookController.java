package org.crazyit.app.controller;

import org.crazyit.app.domain.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Description:<br>
 * 网站: <a href="http://www.crazyit.org">疯狂Java联盟</a><br>
 * Copyright (C), 2001-2020, Yeeku.H.Lee<br>
 * This program is protected by copyright laws.<br>
 * Program Name:<br>
 * Date:<br>
 *
 * @author Yeeku.H.Lee kongyeeku@163.com 公众号: fkbooks<br>
 * @version 1.0
 */
@Controller
public class BookController
{
	// 定义文件上传的目录
	@Value("${file.upload-folder}")
	private String path;	 // ①
	// @PostMapping指定被修饰方法处理/addBook请求
	@PostMapping("/addBook")
	public String add( @Validated Book book, Errors errors, Model model,
					  ServletRequest request) throws IOException
	{
		// 如果文件不是图片
		if (!book.getCover().getContentType().toLowerCase().startsWith("image"))
		{
			errors.rejectValue("cover", null, "只能上传图片");
		}
		// 如果文件大小大于2M
		if (book.getCover().getSize() > 2 * 1024 * 1024)
		{
			errors.rejectValue("cover", null, "图片大小不能超2M");
		}
		// 如果校验失败
		if (errors.getErrorCount() > 0)
		{
			return "index";
		}
		else
		{
			var f = new File(path);
			// 如果path对应的路径不存在，创建该目录
			if (!f.exists())
			{
				f.mkdir();
			}
			System.out.println(f.getAbsolutePath());
			// 调用调用MultipartFile的getOriginalFilename()方法获取原始文件名
			// 然后调用StringUtils的getFilenameExtension获取扩展名
			var extName = StringUtils.getFilenameExtension(
					book.getCover().getOriginalFilename());
			var targetName = UUID.randomUUID().toString()
					+ "." + extName;
			// 调用MultipartFile的transferTo()方法完成文件复制
			book.getCover().transferTo(new File(path + targetName));
			book.setTargetName(targetName);
			System.out.println("添加的图书：" + book.getName());
			model.addAttribute("tip", book.getName() + "图书添加成功！");
			return "success";
		}
	}
}
