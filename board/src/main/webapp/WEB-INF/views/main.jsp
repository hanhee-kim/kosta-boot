<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 메인</title>
<style type="text/css">
	#header {
		height: 57px;
		display: flex;
	}
	a,b{
		display: inline-block;
		line-height: 57px;
	}
</style>
</head>
<body>
<div id="header">
<img src="resources/img/logo.png" width="160px" height="57px">&nbsp;
<c:choose>
	<c:when test="${user eq Empty}">
		<a href="login">로그인</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="join">회원가입</a>&nbsp;&nbsp;&nbsp;
	</c:when>
	<c:otherwise>
		<b>${user.name }</b>&nbsp;&nbsp;<a href="logout">로그아웃</a>
	</c:otherwise>
</c:choose>&nbsp;&nbsp;&nbsp;
<a href="boardlist">게시판 목록</a><br><br>
</div>
</body>
</html>