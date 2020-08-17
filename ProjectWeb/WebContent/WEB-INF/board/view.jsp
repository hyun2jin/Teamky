<%@page import="board.action.BoardModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="board.action.BoardModel" %>
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
<body>

<%
	BoardModel	bm = null;
	Member mem = null;
	String userId = null;
	
	bm = (BoardModel)session.getAttribute("viewinfo");
	mem = (Member)session.getAttribute("userinfo");	
	if(mem != null)
		userId = mem.getMem_nick();
	int pagecnt = (int)session.getAttribute("pagecount") - 1;
%>

<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>



<div class="container" style="border: 1px; float: left; width: 90%; height:600px">
		<div class="row">				
				<table class="table table-striped"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="3"
								style="background-color: #eeeeee; text-align: center;">글 보기 </th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="width: 20%;"> 글 제목 </td>
							<td colspan="2"><%= bm.getBoard_title() %></td>
						</tr>
						<tr>
							<td>작성자</td>
							<td colspan="2"><%= bm.getBoard_id() %></td>
						</tr>
						<tr>
							<td>작성일</td>
							<td colspan="2"><%= bm.getBoard_date() %></td>
						</tr>
						<tr>
							<td>내용</td>
							<%--
							 <td colspan="2" style="min-height: 200px; text-align: left;"><%= bm.getBoard_content() %></td>
							 --%>							 
							 <td><%= bm.getBoard_content() %></td>
							
						</tr>
					</tbody>
				</table>	
				<a href = "boardlist.do?page=<%=pagecnt%>" class="btn btn-primary">목록</a>				

				<%
					//글작성자 본인일시 수정 삭제 가능
					if(userId != null && userId.equals(bm.getBoard_id())){
				%>
					&nbsp;&nbsp;<a href="update.do?boardnum=<%= bm.getBoard_num() %>" class="btn btn-primary"  style="float: right">수정</a>&nbsp;&nbsp;
					<a href="delete.do?boardnum=<%= bm.getBoard_num()%>" class="btn btn-primary"  style="float: right">삭제</a>
				<%
					}
				%>
		</div>

		<jsp:include page="comment.jsp" flush="false"></jsp:include>		
</div>



</body>
</html>