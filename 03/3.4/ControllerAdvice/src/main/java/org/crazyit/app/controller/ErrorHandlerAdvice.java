package org.crazyit.app.controller;

import org.crazyit.app.exception.BookException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

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
@RestControllerAdvice(basePackageClasses = BookController.class)
public class ErrorHandlerAdvice extends ResponseEntityExceptionHandler
{
	// 定义异常处理方法
	@ExceptionHandler(BookException.class)
	public ResponseEntity<?> handle(HttpServletRequest request, Throwable ex)
	{
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null)
		{
			return new ResponseEntity<>(ex.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(statusCode));
	}
}