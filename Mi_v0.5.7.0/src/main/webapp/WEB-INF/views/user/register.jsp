<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원가입</h1>
<form action = "/spring/user/registerProc" method = "get">

<input name = "userName">
<input name = "id">
<input name = "pw">
<input name = "email">
<input type = "submit" value = "회원가입">
</form> 
</body>
</html>