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
<title>Insert title here</title>
</head>

<style>

#div_top{
	width: 100%;
	height:100px;
	text-align: left;
	background-color:rgb(61,65,77);	
}



</style>


<body>


<div id="div_root">
<c:set var="name" value="${sessionScope.userinfo.mem_nick}"></c:set>
<c:if test="${name == null}">
<div id="div_top">
	<br />
	<font size="5" color="white" face="돋움">로그인해주세요</font>
	<br /></div>
</c:if>
<c:if test="${name != null}">
<div id="div_top"><br />
	<font size="5" color="white" face="돋움">${sessionScope.userinfo.mem_nick} 님 로그인</font><br />	
</div>
</c:if>
</div>


</body>
</html>