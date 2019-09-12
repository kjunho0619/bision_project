<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
</head>
<script type="text/javascript">
	$(function() {
		$('#loginCheck').click(loginCheck);
		$('#cancelback').click(cancelback);
	});
	
	function loginCheck() {
		var userid = $('#userid').val();
		var userpwd = $('#userpwd').val();
		if(userid.length < 3 || userid.length > 10){
			alert("아이디는 3~10글자를 입력하세요.");
			return false;
		}
		if(userpwd.length < 3 || userpwd.length > 10){
			alert("비밀번호는 3~10글자를 입력하세요.")
			return false;
		}
		$('#loginForm').submit();
	}
	
	function cancelback() {
		location.href="/moneybook/cancelback";
	}
</script>

<body>
	<h1>[로그인]</h1>
	<form action="/moneybook/login" method="post" id="loginForm">
		아이디 <input type="text" name="userid" id="userid"><br>
		비밀번호 <input type="password" name="userpwd" id="userpwd"><br>
		<input type="button" value="로그인" id="loginCheck">
		<input type="button" value="취소" id="cancelback">
	</form>
</body>
</html>
