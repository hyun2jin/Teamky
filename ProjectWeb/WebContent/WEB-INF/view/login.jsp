<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
#div_con{
	width: 65%;
	height:500px;
	float:right;
	background-color: white;
	background-size: 100%;
	text-align: left;
	border:thin;
}
</style>

<script type="text/javascript">
	
	function checkValue() {
		
		inputForm = eval("document.loginInfo");
		
		if(!inputForm.id.value){
			alert("아이디를 입력하세요");
			inputForm.id.focus();
			return false;
		}
		
		if(!inputForm.password.value) {
			alert("비밀번호를 입력하세요");
			inputForm.password.focus();
		}		
	}
	
	function goJoinForm() {
		location.href = "join.do";
	}
	
	
</script>

<title>로그인</title>
</head>
<body>


<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>


<div id="div_con">
	<form name="loginInfo" method="post" action="login.do" onsubmit="return checkValue()">
	
	<br /><br />
	<b ><font size="6" color="gray">로그인</font></b>
	<br /><br /><br />	
	
	<table>
		<tr>
			<td bgcolor="#98FB98">아이디</td>
			<td><input type="text" name="id" maxlength="50"/></td>
		</tr>
		
		<tr>
			<td bgcolor="#98FB98">비밀번호</td>
			<td><input type="password" name="password" maxlength="50" /></td>
		</tr>		
	</table>
	
	<br />
	<input type="submit" class="btn btn-primary" value="로그인"/>
	<input type="button" class="btn btn-primary" value="회원가입" onclick="goJoinForm()"/>
	
	</form>
	
	<%
		String msg = (String)request.getParameter("msg");
		if(msg != null && msg.equals("-1"))
		{
			out.println("<br/>");
			out.println("<font color='red' size='5'>로그인이 차단됐습니다 고객센터로 문의바랍니다</font>");
		}		
		else if(msg != null && !msg.equals("-2"))
		{
			out.println("<br/>");
			out.println("<font color='red' size='5'>아이딘나 비밀번호가 잘못됐습니다</font>");
		}
		else if(msg != null && !msg.equals("1"))
		{
			out.println("<br/>");
			out.println("<font color='red' size='5'>아이디나 비밀번호를 확인하세요</font>");
		}			
	%>


</div>





</body>
</html>