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

<script type="text/javascript">

 function logOutCheck()
 { 
 	if (confirm("로그아웃 하시겠습니까?") == true)
 	{
     	return true;
 	}
    else
    {   //취소
    	 session.invalidate();
    	 return false;
    }
 }

 </script>
 
 <%
 	Member mem = null;
 	mem = (Member)session.getAttribute("userinfo");
 	String nick = mem.getMem_nick();
 %>



<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>

<div id="div_con">
	<form name="loginInfo" method="post" action="logout.do?name=<%=nick%>" onsubmit="return logOutCheck()">
	<button class="btn btn-primary" id="submit"> 확인 </button>
	</form>
</div>	

</body>
</html>