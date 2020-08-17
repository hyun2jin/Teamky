<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="guest.action.GuestModel" %>

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
	ArrayList<GuestModel> list = null;
	list = (ArrayList)session.getAttribute("guestlist");
%>


<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>


<div style="border: 1px; float: right; width: 80%;">
	<div id="#div_con">
		<br /><br />
		<b ><font size="6" color="gray">방명록 관리</font></b>
		
		<table class="table">
		<thead class="thead-dark">
			<tr>
				<th width="50">번호</th>
				<th width="80">닉네임</th>
				<th width="150">내용</th>
				<th width="100">작성날짜</th>
				<th width="120"></th>
			</tr>
		</thead>
			<% if(list != null && list.size() > 0) {
			 	for(int i=0;i<list.size();i++) {
			 		GuestModel gm = list.get(i); 	%>
					<form action="/" method="post">
					<tr>
						<td><%=gm.getGuestNo() %></td>
						<td><%=gm.getGuestNick() %></td>
						<td><%=gm.getGuestCont() %></td>
						<td><%=gm.getGueestDate() %></td>
						<td>						
							<input name="gnum" type="hidden" value=<%=gm.getGuestNo()%> />
							<input type="submit" class="btn btn-primary" value="삭제" formaction="guestMngDel.do" />
						</td>					
					</tr>
					</form>
					<% } }%>	
		</table>
</body>
</html>