<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="project.model.Member" %>
<%@ page import="java.util.*" %>
<%@ page import="board.action.BoardModel" %>
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
	ArrayList<BoardModel> list = null;
	list = (ArrayList)session.getAttribute("managerlist");
%>


<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>


<div style="border: 1px; float: right; width: 80%;">
	<div id="#div_con">
		<br /><br />
		<b ><font size="6" color="gray">게시판관리</font></b>
		
		<table class="table">
			<thead class="thead-dark">
			<tr>
				<th width="50">번호</th>
				<th width="80">닉네임</th>
				<th width="150">제목</th>
				<th width="100">작성날짜</th>
				<th width="100">상태</th>
				<th width="220"></th>
			</tr>
			</thead>
			<% if(list != null && list.size() > 0) {
			 	for(int i=0;i<list.size();i++) {
			 		BoardModel bm = list.get(i); 	%>
					<form action="/" method="post">
					<tr>
						<td><%=bm.getBoard_num() %></td>
						<td><%=bm.getBoard_id() %></td>
						<td>
						<a href="boardCommentMng.do?boardnum=<%=bm.getBoard_num()%>"><%=bm.getBoard_title() %></a>									
						</td>
						<td><%=bm.getBoard_date() %></td>
						
						<td>
							<% if(bm.isBoard_lock() == true) { %>
								방장김
								<% } else { %>
								정상
								<% } %>						
						</td>					
						<td>
							<input name="boardnum" type="hidden" value=<%=bm.getBoard_num() %>/>
							<input type="submit" class="btn btn-primary" value="게시글삭제" onclick="info(this)" formaction="boardMDelete.do">
							<%if(bm.isBoard_lock() == false)  { %>			
								<input type="submit" class="btn btn-primary" value="게시글잠금" formaction="boardMLock.do">
							<% } else { %>
								<input type="submit" class="btn btn-primary" value="게시글잠금해제" formaction="boardMUnLock.do">
							<% } %>						
						</td>					
					</tr>		
					</form>
			<% } }%>					
		</table>			
			
			
			
			
			
</body>
</html>