<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="board.action.BoardModel" %>
<%@ page import="project.model.Member" %>
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

<title>commentex</title>



</head>
<body>


<%
	BoardModel	bm = null;
	Member mem = null;
	String userId = null;
	ArrayList<CommentExModel> list = null;
	CommentExModel cmmod = null;
	
	
	cmmod = (CommentExModel)session.getAttribute("modisubcom");
	bm = (BoardModel)session.getAttribute("viewinfo");
	mem = (Member)session.getAttribute("userinfo");
	list = (ArrayList)session.getAttribute("commentex");
	if(bm != null)
		userId = bm.getBoard_id();
	
	String scm = (String)request.getParameter("cmnum");
	int cmm = 0;
	if(scm != null)
		cmm = Integer.parseInt(scm);
%>



<% if(list != null && list.size() > 0) { %>
			
	<% for(int i=0;i<list.size();i++) {
		CommentExModel cm = list.get(i); 		
		if(cm.getComment_commnum() == cmm) { %>
		<%--아이디, 작성날짜 --%>
		<td width="150">
			<div >
				<img src="/images/icon/re.png" alt="" />
				<%=cm.getComment_nick() %> <br />
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				&nbsp&nbsp<font size="2" color="lightgray"><%=cm.getDate() %></font>
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
				<%
					//글작성자 본인일시 수정 삭제 가능
					if(mem != null && mem.getMem_nick().equals(cm.getComment_nick()) && bm.isBoard_lock() == false){
				%>	
					<a href="modifySubComment.do?cmindex=<%=i%>">[수정]</a><br />
					<a href="deleteSubComment.do?cmindex=<%=i%>">[삭제]</a>
				<%
					}
				%>
			</div>			
		</td>		
		</tr>		
		<%if(cmmod != null && cmmod.getComment_num() == cm.getComment_num())  { %>
		<%=cmmod.getComment_num() %>
		<%=cm.getComment_num()  %>		
		<tr>
			<td>
			<form action="modifySubComment.do" method="post">	
				<textarea name="comment_modify" cols="70" rows="4"><%=cmmod.getContent() %></textarea>			
				<br /><input type="submit" class="btn btn-primary" value="확인"/>
			</form>
			</td>
		</tr>	
		<%} %>			
				
		<% } }  } %>

		
		
		

</body>
</html>