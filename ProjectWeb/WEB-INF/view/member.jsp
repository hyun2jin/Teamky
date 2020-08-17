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

#div_root{
	margin: auto;
	width: auto;
}

#div_top{
	width: 100%;
	height:100px;
	text-align: right;
	background-color:#98FB98
	
}

#div_menu{
	width:50%;
	height:500px;
	background-color: white;
	text-align: left;
	border:thin;
}

#div_con{
	width: 80%;
	height:500px;
	float:right;
	background-color: white;
	background-size: 100%;
	text-align: center;
	border:thin;
}
</style>




<title>회원 가입</title>


<script type="text/javascript">
	
	function checkValue()
	{
		if(!document.userInfo.id.value) {
			alert("아이디를 입력하세요");
			return false;
		}
		
		if(!document.userInfo.password.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		if(document.userInfo.password.value != doucment.userInfo.passwordcheck.value) {
			alert("비밀번호를 동일하게 입력하세요");
			retrun false;
		}
	}
	
</script>


</head>
<body>




<div id="div_root">
<div id="div_top"><br /><br />로그인상태</div>
</div>


<div style="border: 1px; float: left; width: 10%">
	<div id="div_menu">
		<br />
		
		
		<c:if test="${user.login == 1}">
			<a href="/"> 로그아웃</a> <br />			
		</c:if>
		<c:if test="${empty user.login }">
			<a href="/"> 로그인</a> <br />
			<a href="join.do"> 회원가입</a> <br />
		</c:if>		
		
		<a href="/"> 출석부</a> <br />
		<a href="/"> 자유게시판</a> <br />
		<a href="/"> 방명록</a> <br />
		
		<c:if test="${user.admin == 1}">
		<a href="/">회원관리</a> <br />	
		<a href="/">로그인기록</a> <br />
		</c:if>	
	</div>
</div>

<div style="border: 1px; float: right; width: 80%;">
	<div id="wrap">
		<br /><br />
		<b ><font size="6" color="gray">회원가입</font></b>
		<br /><br /><br />
		
		<form method="post" action="join.do" name="userInfo" onsubmit="return checkValue()">
			<table>
				<tr>
					<td id="title">아이디</td>
					<td style="text-align: left;">
						<input type="text" name="id" maxlength="50">
						<input type="button" class="btn btn-primary" value="중복확인">
					</td>
				</tr>
				
				<tr>
					<td id="title">닉네임</td>
					<td style="text-align: left;">
						<input type="text" name="nick" maxlength="50">
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
						<input type="text" name="mail1" maxlength="50">
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
						<input type="text" name="phone"/>
					</td>
				</tr>
				
			</table>
			<br />
			<input type="submit" class="btn btn-primary" value="가입"/>
			<input type="button" class="btn btn-secondary" value="취소">
			
		</form>
	</div>
</div>





</body>
</html>