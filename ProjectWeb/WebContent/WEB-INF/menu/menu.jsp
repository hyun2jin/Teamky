<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="project.model.Member" %>
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

#div_menu{
	width:100%;
	height:1000px;
	background-color: rgb(247,247,249);
	text-align: left;
}

</style>

<body>

<%
	Member mem = null;
	mem = (Member)session.getAttribute("userinfo");
	int mem_att = 0;
	if(mem != null)
		mem_att = mem.getMem_att();

%>



<div style="border: 1px; float: left; width: 10%">
	<div id="div_menu">
		<c:set var="name" value="${sessionScope.userinfo.mem_nick}"></c:set>
		<br />
		<c:if test="${name != null}">
			<div>
				<a href="myinfomodify.do">::내정보수정</a><br />
				<a href="logout.do">::로그아웃</a> <br />
				<a href="guestchulcheck.do">::출석부</a> <br />
				<a href="boardlist.do">::자유게시판</a> <br />
				<a href="guestbook.do">::방명록</a> <br />
			</div>
		</c:if>		
		<c:if test="${name == null}">
			<div>			
				<a href="login.do">::로그인</a> <br />
				<a href="join.do">::회원가입</a> <br />
				<a href="guestchulcheck.do">::출석부</a> <br />
				<a href="boardlist.do">::자유게시판</a> <br />
				<a href="guestbook.do">::방명록</a> <br />			
			</div>
		</c:if>	
		
				
		<%if(mem_att == 1) { %>
			<br /><br /><br />
			<a href="membermana.do">::회원관리</a> <br />
			<a href="boardlistmanage.do">::게시글 관리</a><br />
			<a href="guestManager.do">::방명록 관리</a><br />
			<a href="chulReset.do">::출석부 초기화</a><br />
			<a href="UserLog.do">::로그인기록</a> <br />
		<% } %>	
	</div>
</div>

</body>
</html>