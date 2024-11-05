<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
글읽기
<hr>
글번호 : ${bno}
<hr>
글내용 : ${btext}
<hr>

<a href = "/spring/guest/del?bno=${bno}">글삭제</a>
<a href = "/spring/guest/modify?bno=${bno}">글수정</a>
<a href = "/spring/guest/getList">글리스트</a>
</body>
</html>