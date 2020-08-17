<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="project.model.Member" %>
<%@ page import="java.util.*" %>
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
  table {
    width: 100%;
    border: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border: 1px solid #444444;
  }
</style>

<title>Insert title here</title>
</head>
<body>


<div class="container" style="border: 1px; float: left; width: 90%; height:500px">
<%
	ArrayList<Member> list = null;
	list = (ArrayList)session.getAttribute("memlist");

%>


<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>


<div style="border: 1px; float: right; width: 90%;">
	<div id="#div_con">
		<br /><br />
		<b ><font size="6" color="gray">회원정보</font></b>
		
		<table class="table">
			<thead class="thead-dark">
			<tr>
				<th width="80">아이디</th>
				<th width="80">닉네임</th>
				<th width="80">이메일</th>
				<th width="100">연락처</th>
				<th width="100">회원상태</th>
				<th width="180"></th>
			</tr>
			</thead>
			
			
			<% if(list != null && list.size() > 0) {
			 	for(int i=0;i<list.size();i++) {
					Member mb = list.get(i); 	%>
					<form action="/" method="post">
					<tr>
					<td><%=mb.getMem_id()%></td>
					<td><%=mb.getMem_nick()%></td>
					<td><%=mb.getEmail() %></td>
					<td><%=mb.getPhone() %></td>
					<td>
						<% if(mb.getMem_st() == 1) { %>
							정상유저
							<% } else { %>
							차단유저
							<% } %>						
					</td>					
					<td>
						<input name="nick" type="hidden" value=<%=mb.getMem_nick() %>/>
						<input type="submit" class="btn btn-primary" value="회원삭제" onclick="info(this)" formaction="memberdel.do">
						<%if(mb.getMem_st() == 1)  { %>			
							<input type="submit" class="btn btn-primary" value="로그인금지" formaction="memberlogde.do">
						<% } else { %>
							<input type="submit" class="btn btn-primary" value="로그인금지해제" formaction="memberlogok.do">
						<% } %>						
					</td>					
					</tr>		
					</form>
			<% } }%>					
		</table>
			
</body>
</html>