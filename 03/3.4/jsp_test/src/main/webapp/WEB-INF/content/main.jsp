<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>登录成功</title>
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css"/>
	<script type="text/javascript" src="/webjars/jquery/jquery.js"></script>
	<script type="text/javascript" src="/webjars/popper.js/umd/popper.min.js"></script>
	<script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<img src="/logo.png" class="rounded mx-auto d-block"><h4>登录成功</h4>
	欢迎您， <span>${sessionScope.name}</span>，登录成功 <br>
	您的角色是：<span><c:choose>
			<c:when test="${sessionScope.role} == 'admin'">系统管理员</c:when>
			<c:when test="${sessionScope.role} == 'manager'">经理</c:when>
			<c:otherwise>普通员工</c:otherwise>
	</c:choose></span>
	<a href="/viewBooks">查看图书</a>
</div>
</body>
</html>
