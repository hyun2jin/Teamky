<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="board.action.BoardModel" %>
<%@ page import="board.action.CommentModel" %>
<%@ page import="board.action.CommentExModel" %>
<%@ page import="java.util.*"%>
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
	String bm = null;
	ArrayList<CommentModel> list = null;
	ArrayList<CommentExModel> listex = null;
	
	list = (ArrayList)session.getAttribute("comment");
	listex = (ArrayList)session.getAttribute("commentex");
	bm = (String)session.getAttribute("cmboard");

%>




<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>



<div class="container" style="border: 1px; float: left; width: 90%; height:600px">
	<div id="#div_con">
		<br /><br />
		<b ><font size="6" color="gray"><%=bm%>번 게시글 댓글</font></b>
		<br />
	</div>
	<%--댓글 리스트 --%>
	<% if(list != null && list.size() > 0) { %>
		<table class="table">
		<thead class="thead-dark">
		<tr>
			<th width="50">순번</th>
			<th width="80">작성자</th>		
			<th width="150">내용</th>
			<th width="100">작성일</th>
			<th width="130"></th>
		</tr>
		</thead>
		<%
			for(int i=0;i<list.size();i++) {
			CommentModel cm = list.get(i);	
		%>
		
		<tr bgcolor="lightblue">
			<form action="/" method="post">	
			<td><%=cm.getComment_num() %></td>
			<td><%=cm.getComment_nick() %></td>
			<td><%=cm.getContent() %></td>
			<td><%=cm.getDate() %></td>
			<td>
			<input name="boardnum" type="hidden" value=<%=bm %>/>
			<input name="comnum" type="hidden" value=<%=cm.getComment_num() %>/>		
			<input type="submit" class="btn btn-primary" value="삭제" formaction="boardCmtMngDelete.do" />
			</td>
			</form>
		</tr>
		
		<% if(listex != null  && listex.size() > 0) {
			
			int count = 0;
			
			for(int j=0;j<listex.size();j++) {
				CommentExModel md = listex.get(j);				
				if(md.getComment_commnum() == cm.getComment_num()) {
					count++;		%>
				<tr>
					<form action="" method="post">
					<td><%=cm.getComment_num()%>-<%=count%></td>
					<td><%=md.getComment_nick() %></td>
					<td><%=md.getContent() %></td>
					<td><%=md.getDate() %></td>					
					<td>
					<input name="boardnum" type="hidden" value=<%=md.getComment_board() %>/>
					<input name="comparent" type="hidden" value=<%=md.getComment_commnum() %>/>		
					<input name="comnum" type="hidden" value=<%=md.getComment_num() %>/>
					<input type="submit" class="btn btn-primary" value="삭제" formaction="deleteMngSubComment.do" />
					</td>					
					</form>				
				</tr>				
			<% } } }%>
		
		
	<% } }%>
		</table>
	<% if(list != null && list.size() == 0) { %>
		<p>댓글이 없습니다</p>
		<table>
		<tr>
			<th width="20">순번</th>
			<th width="50">작성자</th>		
			<th width="100">내용</th>
			<th width="100">작성일</th>
			<th width="100"></th>
		</tr>	
		</table>
	<% } %>
	
	<a href = "boardlistmanage.do" class="btn btn-primary">목록</a>
	
</div>

</body>
</html>
