<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="board.action.BoardModel" %>
<%@ page import="project.model.Member" %>
<%@ page import="board.action.CommentModel" %>
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


ess.setAttribute("comment", list);
#main {
	float:left;
	width:950px;
	height:600px;
	text-align:center;
	vertical-align:middle
	overflow:auto;
}

table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
  }

</style>
<title>comment</title>
</head>
<body>


<%
	BoardModel	bm = null;
	Member mem = null;
	String userId = null;
	ArrayList<CommentModel> list = null;
	
	bm = (BoardModel)session.getAttribute("viewinfo");
	mem = (Member)session.getAttribute("userinfo");
	list = (ArrayList)session.getAttribute("comment");
	if(bm != null)
		userId = bm.getBoard_id();
%>


<%
	String moduser = null;
	String num = null;
	String cmuser = null;
	int mnum = 0;
	int cnum = 0;

	moduser = (String)session.getAttribute("modifyuser");
	cmuser = (String)session.getAttribute("addcommentex");
	num = (String)request.getParameter("mnum");
	
	if(num != null)
		mnum = Integer.parseInt(num);
	
	if(cmuser != null)
		cnum = Integer.parseInt(cmuser);
%>

<table id="main">
<%--댓글 리스트 --%>

<% if(list != null && list.size() > 0) { 
	for(int i=0;i<list.size();i++) {
		CommentModel cm = list.get(i); %>
	<tr bgcolor="lightblue">
		<%--아이디, 작성날짜 --%>
		<td width="150">
			<div>	
				<%=cm.getComment_nick() %><br />
				<font size="2" color="lightgray"><%=cm.getDate() %></font>
			</div>
		</td>	
		<%--본문내용 --%>
		<td width="550">
			<div>				
				<%=cm.getContent() %>
			</div>
		</td>
		<%-- 버튼 --%>
		<td width="100">
			<div id="btn" style="text-align:center;">
				<% if(mem != null && bm.isBoard_lock() == false) {%>
					<a href="addSubComment.do?cmnum=<%=cm.getComment_num()%>">[답글]</a><br />
				<%} %>
								
				<%
					//글작성자 본인일시 수정 삭제 가능
					if(mem != null && mem.getMem_nick().equals(cm.getComment_nick()) && bm.isBoard_lock() == false)
					{
				%>					
					<a href="modifycomment.do?mnum=<%=cm.getComment_num()%>">[수정]</a><br />
					<a href="deletecomment.do?cnum=<%=cm.getComment_num()%>">[삭제]</a>
				<%
					}
				%>
			</div>			
		</td>
		<%--댓글수정 --%>
		<%
			if(moduser != null && mnum == cm.getComment_num()) { %>
			<tr><td>
			<form action="modifycomment.do" method="post">	
				<textarea name="comment_modify" cols="70" rows="4"><%=cm.getContent() %></textarea>			
				<br /><input type="submit" class="btn btn-primary" value="확인"/>
			</form>
			</td></tr>		
		<% } %>
		<%--대댓글 입력 --%>
		<%
			if(cmuser != null && cnum == cm.getComment_num()) { %>
			<tr><td>
			<form action="addSubComment.do" method="post">	
				<textarea name="comment_addreply" cols="70" rows="4"></textarea>			
				<br /><input type="submit" class="btn btn-primary" value="댓글등록"/>
			</form>				
			</td></tr>		
		<% } %>
		
		<%--대댓글 --%>		
		<tr>			
		<jsp:include page="commentEx.jsp" flush="false">
	    	<jsp:param name="cmnum" value="<%=cm.getComment_num()%>" />
		</jsp:include>
		</tr>						
	</tr>
	
<% } } %>
</table>


<%if(list != null && list.size() == 0) { %>
	<p>댓글이 없습니다</p>
<%} %>
	


<%-- 댓글수정 --%>
<% if(userId != null ) {  %>


<% } %>

 
 
 
<% if(bm.isBoard_lock() == true) { %>
 	<p>글이 잠금상태이므로 댓글서비스를 이용할수없습니다</p>
<% } else { %>

<% if( userId != null) { %>
<c:if test="${sessionScope.addcommentex == null}">
	<form action="writecomment.do?comment=comment_content" method="post">
		<div>
			${sessionScope.sessionId}
		</div>	
		<div>
			<textarea name="comment_content" cols="70" rows="4"></textarea>
		</div>
		<input type="submit" class="btn btn-primary" value="등록"/>
	</form>
</c:if>
<%} }%>




</body>
</html>