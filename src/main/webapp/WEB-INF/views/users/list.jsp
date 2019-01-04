<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>userInfo</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

</head>

<body>
	<table border="1">
		<tr>
			<td>uid</td>
			<td>uname</td>
			<td>upass</td>
			<td>uaddress</td>
		</tr>
		<c:forEach items="${pageInfo.list}" var="uinfo">
			<tr>
				<td>${uinfo.uid}</td>
				<td>${uinfo.uname}</td>
				<td>${uinfo.upass}</td>
				<td>${uinfo.address}</td>
			</tr>
		</c:forEach>
	</table>

	<p>
	<ul>
		<li>当前页:${pageInfo.pageNum}</li>
		<li>总页数:${pageInfo.pages}</li>
		<li><a href="/user/list/1/${pageInfo.pageSize}">首页</a></li>

		<c:if test="${pageInfo.hasNextPage}">
			<li><a
				href="/user/list/${pageInfo.nextPage}/${pageInfo.pageSize}">下一页</a></li>
		</c:if>

		<c:if test="${pageInfo.hasPreviousPage}">
			<li><a
				href="/user/list/${pageInfo.prePage}/${pageInfo.pageSize}">上一页</a></li>
		</c:if>
		<li><a href="/user/list/${pageInfo.pages}/${pageInfo.pageSize}">最后页</a>
		</li>
	</ul>
	</p>
</body>
</html>
