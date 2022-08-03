package org.crazyit.firstboot.controller;

import org.crazyit.firstboot.service.BookService;
import org.crazyit.firstboot.domain.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
@Controller
public class BookController
{
	@GetMapping("/")
	public String index(Model model)
	{
		model.addAttribute("tip", "欢迎访问第一个Spring Boot应用");
		return "hello";
	}
	@GetMapping("/rest")
	@ResponseBody
	public ResponseEntity restIndex()
	{
		return new ResponseEntity<>("欢迎访问第一个Spring Boot应用",
				null, HttpStatus.OK);
	}
	@Autowired
	private BookService bookService;
	@PostMapping("/addBook")
	public String addBook(Book book, Model model)
	{
		bookService.addBook(book);
		return "redirect:listBooks";
	}
	@PostMapping("/rest/books")
	@ResponseBody
	public ResponseEntity<Map<String, String>> restAddBook(@RequestBody Book book)
	{
		bookService.addBook(book);
		return new ResponseEntity<>(Map.of("tip", "添加成功"),
				null, HttpStatus.OK);
	}

	@GetMapping("/listBooks")
	public String list(Model model)
	{
		model.addAttribute("books", bookService.getAllBooks());
		return "list";
	}
	@GetMapping("/rest/books")
	@ResponseBody
	public ResponseEntity<List<Book>> restList()
	{
		return new ResponseEntity<>(bookService.getAllBooks(),
				null, HttpStatus.OK);
	}

	@GetMapping("/deleteBook")
	public String delete(Integer id)
	{
		bookService.deleteBook(id);
		return "redirect:listBooks";
	}
	@DeleteMapping("/rest/books/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> restDelete(@PathVariable Integer id)
	{
		bookService.deleteBook(id);
		return new ResponseEntity<>(Map.of("tip", "删除成功"),
				null, HttpStatus.OK);
	}
}
