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


<%
	Member mem = null;
	String userid = null;
	
	mem = (Member)session.getAttribute("userinfo");
	if(mem != null)
		userid = mem.getMem_nick();
	
%>

<% if(userid != null) {  %>
<div style="border: 1px; float: right; width: 80%;">
	<div id="#div_con">
		<br /><br />
		<b ><font size="6" color="gray">내정보수정</font></b>
		<br /><br /><br />
		
		<form method="post" action="myinfomodify.do" name="userInfo" onsubmit="return checkValue()">
			<table>
				<tr>
					<td id="title">아이디</td>
					<td style="text-align: left;">
						<input type="text" name="id" maxlength="50" value="<%=mem.getMem_id()%>" readonly>
					</td>
				</tr>
				
				<tr>
					<td id="title">닉네임</td>
					<td style="text-align: left;">
						<input type="text" name="nick" maxlength="50" value="<%=mem.getMem_nick() %>">
						<input type="button" class="btn btn-primary" value="중복확인">
					</td>
				</tr>
				
				<tr>
					<td id="title">비밀번호</td>
					<td style="text-align: left;">
						<input type="password" name="password" maxlength="50">
					</td>
				</tr>
					
				<tr>
					<td id="title">비밀번호 확인</td>
					<td style="text-align: left;">
						<input type="password" name="passwordcheck" maxlength="50">
					</td>					
				</tr>
					
				<tr>
					<td id="title">이메일</td>
					<td style="text-align: left;">
						<input type="text" name="mail1" maxlength="50" value=<%=mem.getEmail() %>>
						<select name="mail2" id="">
							<option>naver.com</option>
							<option>daum.com</option>
							<option>gmail.com</option>
							<option>nate.com</option>							
						</select>						
					</td>					
				</tr>
					
				<tr>
					<td id="title">휴대전화</td>
					<td style="text-align: left;">
						<input type="text" name="phone" value=<%=mem.getPhone()%> />
					</td>
				</tr>
				
			</table>
			<br />
			<input type="submit" class="btn btn-primary" value="정보수정"/>  
			<input type="button" class="btn btn-secondary"value="취소">
			
		</form>
	</div>
</div>

<% } else {  %>
 <p>로그인후 이용가능합니다</p>
 <% } %>




</body>
</html>