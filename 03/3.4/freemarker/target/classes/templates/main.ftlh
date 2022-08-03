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
	<!-- 使用${Session['name']}访问Session范围的指定属性  -->
	欢迎您， <span>${Session['name']}</span>，登录成功 <br>
	您的角色是： <#switch Session['role']>
        <#case 'admin'>
			<span>系统管理员</span>
            <#break>
        <#case 'manager'>
			<span>经理</span>
            <#break>
        <#default>
			<span>普通员工</span>
    </#switch>
	<a href="/viewBooks">查看图书</a>
</div>
</body>
</html>
