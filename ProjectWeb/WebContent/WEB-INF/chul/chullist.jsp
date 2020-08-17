<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import ="java.util.*" %>
<%@ page import ="chulcheck.action.ChulCheckModel" %>
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
	ArrayList<ChulCheckModel> list = null;
	list = (ArrayList)session.getAttribute("chullist");
%>
<br /><br />
<table class="table">
	<thead class="thead-dark">
	<tr>		
		<th scope="col">등수</th>
		<th scope="col">출석시간</th>
		<th scope="col">닉네임</th>
		<th scope="col">내용</th>
		<th scope="col">출석일수</th>
		<th scope="col">아이피</th>		
	</tr>
	</thead>
	
	<%if(list != null && list.size() > 0) {
		for(int i=0;i<list.size();i++) {
			ChulCheckModel cm = list.get(i); 
		%>
	<tr>
		<td><%=cm.getChul_no() %></td>
		<td><%=cm.getChul_date() %></td>
		<td><%=cm.getChul_id() %></td>
		<td><%=cm.getChul_cont() %></td>
		<td><%=cm.getChul_day() %></td>
		<td><%=cm.getChul_ip() %></td>
	</tr>
	<% }} %>	
</table>



</body>
</html>