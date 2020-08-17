<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="board.action.BoardModel" %>
<%@ page import="project.model.Member" %>
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



<title>전체 게시글</title>
</head>
<body>

<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>


<%
			//로긴한사람이라면	 userID라는 변수에 해당 아이디가 담기고 그렇지 않으면 null값

			String userID = null;
			Member mem = null;
			if (session.getAttribute("userinfo") != null) {
				mem = (Member)session.getAttribute("userinfo");
				if(mem != null)
					userID = mem.getMem_nick();
			}
			
			int pagecount = 0;
			pagecount = (int)session.getAttribute("pagecount");
		%>





<div class="container" style="border: 1px; float: left; width: 90%; height:600px; margin-left:10px;">
	<div class = "row">
		
		<br /><br />
		<b ><font size="6" color="gray">자유게시판</font></b>
		<br /><br /><br />	
		
		<table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
			<thead class="thead-dark">
				<tr>
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<%					
					ArrayList<BoardModel> list = (ArrayList)session.getAttribute("boardlist");
					if(list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						BoardModel bm = list.get(i);						
					%>					
					<tr>
						<td width=10%><%=bm.getBoard_num()%></td>
						<td width=50%><a href="view.do?boardnum=<%=bm.getBoard_num()%>"><%=bm.getBoard_title()%></a></td>
						<td width=10%><%=bm.getBoard_id()%></td>
						<td width=20%><%=bm.getBoard_date()%></td>
						<td width=10%><%=bm.getBoard_read()%></td>	
					</tr>
					<%
						} }
					%>			
			</tbody>

		</table>		
	</div>
</div>	

<% if(userID != null ) { %>
<div class="pageview" style="border: 1px; float: right; width: 85%;">
	<c:forEach var="i" begin="1" end="${pagecount}">
   		<a href="boardlist.do?page=${i}">[${i}]</a>
	</c:forEach>		
	
	<c:set var="name" value="${sessionScope.userinfo.mem_nick}"></c:set>
	
	<c:if test="${name != null}">	
		<a href = "write.do" class="btn btn-primary pull-right">글쓰기</a>
	</c:if>
	
	<c:if test="${name == null}">	
		<a href = "" class="btn btn-secondary pull-right">글쓰기</a>
	</c:if>
</div>					
<% } %>

</body>
</html>