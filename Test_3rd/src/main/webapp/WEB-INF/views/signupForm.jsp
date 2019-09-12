<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src='<c:url value="/js/jquery-3.4.1.js" />'></script>
</head>
<script>
	function signupClick() {
		var userid = $('#userid').val();
		var userpwd = $('#userpwd').val();
		var userpwdcheck = $('#userpwdcheck').val();
		
		if(userid.length < 3 || userid.length > 10){
			alert("아이디는 3~10글자를 입력하세요.");
			return false;
		}
		
		if(userpwd.length < 3 || userpwd.length > 10){
			alert("비밀번호는 3~10글자를 입력하세요.")
			return false;
		}
		
		if(userpwd != userpwdcheck){
			alert("비밀번호가 일치하지 않습니다.")
			return false;
		}
		
		$('#signupForm').submit();
		
	}
</script>
<body>
	<h1>[회원가입]</h1>
	<form action="/moneybook/signup" method="post" id="signupForm">
		아이디 <input type="text" id="userid" name="userid"><br>
		비밀번호 <input type="password" id="userpwd" name="userpwd"><br>
		비밀번호 확인 <input type="password" id="userpwdcheck" name="userpwdcheck"><br>
		<input type="button" value="가입" onclick="signupClick()"> <input
			type="button" value="취소"
			onclick="location.href='/moneybook/cancelback'">
	</form>
</body>
</html>
