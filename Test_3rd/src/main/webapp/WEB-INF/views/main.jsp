<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
</head>
<body>
	<h1>[일일 가게부]</h1>
	<c:if test="${sessionScope.userid != null}">
		<h2>${sessionScope.userid}님 환영합니다!</h2>
		<a href="/moneybook/mymoneybook">내 가계부</a>
		<br>
		<a href="/moneybook/logout">로그아웃</a>
		<br>
	</c:if>

	<c:if test="${sessionScope.userid == null}">
		<a href="/moneybook/signupForm">회원가입</a>
		<br>
		<a href="/moneybook/loginForm">로그인</a>
	</c:if>
</body>
</html>
