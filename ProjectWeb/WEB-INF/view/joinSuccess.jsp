<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<style>

#div_root{
	margin: auto;
	width: auto;
}

#div_top{
	width: 100%;
	height:100px;
	text-align: right;
	background-color:#98FB98
	
}

#div_menu{
	width:50%;
	height:500px;
	background-color: white;
	text-align: left;
	border:thin;
}

#div_con{
	width: 80%;
	height:500px;
	float:right;
	background-color: white;
	background-size: 100%;
	text-align: center;
	border:thin;
}
</style>

<title>회원가입성공</title>
</head>
<body>



<div id="div_root">
<div id="div_top"><br /><br />로그인상태</div>
</div>


<div style="border: 1px; float: left; width: 10%">
	<div id="div_menu">
		<br />
		
		
		<c:if test="${user.login == 1}">
			<a href="/"> 로그아웃</a> <br />			
		</c:if>
		<c:if test="${empty user.login }">
			<a href="/"> 로그인</a> <br />
			<a href="join.do"> 회원가입</a> <br />
		</c:if>		
		
		<a href="/"> 출석부</a> <br />
		<a href="/"> 자유게시판</a> <br />
		<a href="/"> 방명록</a> <br />
		
		<c:if test="${user.admin == 1}">
		<a href="/">회원관리</a> <br />	
		<a href="/">로그인기록</a> <br />
		</c:if>	
	</div>
</div>

<div style="border: 1px; float: right; width: 80%;">

<form action="index.jsp">
<% String name = (String)request.getAttribute("usernick"); %>
<h3><%=name %>님 회원가입을 환영합니다 </h3>
<input type="submit" class="btn btn-primary" value="확인"/>
</form>

</div>
 


</body>
</html>