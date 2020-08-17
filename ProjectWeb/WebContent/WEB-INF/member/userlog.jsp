<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*" %>
<%@ page import="project.model.UserLogModel" %>
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

<%
	ArrayList<UserLogModel> list = null;
	list = (ArrayList)session.getAttribute("userlog");
%>


<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>

<div class="container" style="border: 1px; float: left; width: 90%; height:500px">
	<div class = "row">
		
		<br /><br />
		<b ><font size="6" color="gray">유저로그</font></b>
		<br /><br /><br />
		
		<table>
			<tr>
				<th width="50">순번</th>
				<th width="80">이름</th>				
				<th width="150">내용</th>
				<th width="80">날짜</th>
				<th width="80">아이피</th>
			</tr>
		
		<% if(list != null && list.size() > 0) {
			 for(int i=0;i<list.size();i++) {
			 UserLogModel um = list.get(i);	 %>
			 <tr>
			 	<td><%=um.getNum()%></td>
			 	<td><%=um.getNick()%></td>
			 	<td><%=um.getCont() %></td>
			 	<td><%=um.getDate() %></td>
			 	<td><%=um.getIp() %></td>
			 </tr>
			 <% } } %>
		</table>
	</div>
</div>

</body>
</html>