<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>
<div class="container" style="border: 1px; float: left; width: 90%; height:500px">
<%
	Member mem = null;
	int check = 0;
	String userid = null;
	
	try {
		mem = (Member)session.getAttribute("userinfo");	
		check = (int)session.getAttribute("check1");		
	} catch (Exception e){
		e.printStackTrace();
	}
	


	if(mem != null)
		userid = mem.getMem_nick();	
%>


<%if(check == 0) { %>
<% if(mem != null) { %>

 <br>
    <b><font size="6" color="gray">출석체크</font></b>
    <hr size="1" width="700">
    <br>


<form action="guestchulcheck.do" method="post">
	
	<input type="hidden" name="name" readonly value=<%=userid%> />
	<p>한줄남기기</p>
	<input type="text" name="content" required/>
	<td><input type="submit" class="btn btn-primary" value="등록" width="300"/></td>	
</form>
<% } else { %>
	<br />
	<p>로그인후 이용가능합니다</p>
<% } %>	

<% } else { %>
	<br />
	<p> 이미 출석체크를 하셨습니다</p>
<% } %>

<jsp:include page="chullist.jsp" flush="false"></jsp:include>

</div>

</body>
</html>