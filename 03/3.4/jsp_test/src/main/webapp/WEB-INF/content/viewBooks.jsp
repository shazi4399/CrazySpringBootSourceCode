<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>查看图书</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/webjars/jquery/jquery.js"></script>
	<script type="text/javascript" src="/webjars/popper.js/umd/popper.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<table class="table table-hover">
		<tr>
			<th>序号</th>
			<th>书名</th>
			<th>作者</th>
			<th>价格</th>
			<th>封面</th>
		</tr>
		<c:forEach items="${bookList}" var="book" varStatus="bookStat">
		<tr>
			<td>${bookStat.index + 1}</td>
			<td>${book.title}</td>
			<td>${book.author}</td>
			<td>${book.price}</td>
			<td><img alt="封面" src="/images/${book.cover}"
				width="70" height="100"></td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
