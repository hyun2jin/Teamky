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
<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<style>
#div_con{
	width: 100%;
	height:500px;
	float:right;
	background-color: white;
	background-size: 100%;
	text-align: center;
	border:thin;
}
</style>






<title>회원 가입</title>






</head>
<body>



<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>

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
			return false;
		}
	}
	
	function nickCheck() {
		
 		var nick = document.userInfo.nick.value;	 
		
		 
		 if(nick.length<1 || nick==null){
		  alert("중복체크할 닉네임을 입력하십시오");
		  return false;
		 }
		 var url = "nickcheck.jsp?nick=" + nick;
		 

		 var _width = '300';
		 var _height = '180';
		 
	    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
	    var _left = Math.ceil(( window.screen.width - _width )/2);
	    var _top = Math.ceil(( window.screen.height - _height )/2); 
	 
	    window.open(url, "get", 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
		 
		return true;	
	}
	
	
	function idCheck(){
		
		 var id = document.userInfo.id.value;	 
		
		 
		 if(id.length<1 || id==null){
		  alert("중복체크할 아이디를 입력하십시오");
		  return false;
		 }
		 var url = "idCheck.jsp?id=" + id;
		 

		 var _width = '300';
		 var _height = '180';
		 
	    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
	    var _left = Math.ceil(( window.screen.width - _width )/2);
	    var _top = Math.ceil(( window.screen.height - _height )/2); 
	 
	    window.open(url, "get", 'width='+ _width +', height='+ _height +', left=' + _left + ', top='+ _top );
		 
		return true;
	}


</script>




<div style="border: 1px; float: right; width: 80%;">
	<div id="#div_con">
		<br /><br />
		<b ><font size="6" color="gray">회원가입</font></b>
		<br /><br /><br />
	
		<form method="post" action="join.do" name="userInfo" onsubmit="return checkValue()">
			
		
			<table>
				<tr>
					<td id="title">아이디</td>
					<td style="text-align: left;">
					<input type="text" name="id" maxlength="50">
					<input type="button" class="btn btn-primary" value="중복확인" onclick="idCheck()">
					</td>
				</tr>
				
				<tr>
					<td id="title">닉네임</td>
					<td style="text-align: left;">
						<input type="text" name="nick" maxlength="50">
						<input type="button" class="btn btn-primary" value="중복확인" onclick="nickCheck()">
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
			<input type="button" class="btn btn-secondary" value="취소"/>			
		</form>
	</div>
</div>





</body>
</html>