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

<style>
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

<title>Insert title here</title>
</head>
<body>

	<%
		//로긴한사람이라면	 userID라는 변수에 해당 아이디가 담기고 그렇지 않으면 null값
		String userID = null;
		if (session.getAttribute("userinfo") != null) {
			
			Member mem = null;
			mem = (Member)session.getAttribute("userinfo");			
			userID = (String)mem.getMem_nick();
		}		
	%>

	<!-- 게시판 -->
	

<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>

	<div style="border: 1px; float: left; width: 80%;">
		<div id="#div_con">
			<form method="post" action="write.do" enctype="multipart/form-data">
				<table class="table"
					style="text-align: center; border: 1px solid #dddddd">
					<thead>
						<tr>
							<th colspan="2"
								style="background-color:#eeeeee; text-align: center;">글쓰기</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td><input type="text" class="form-control" placeholder="글 제목" name="bbsTitle" maxlength="50" style=""/></td>
						</tr>
						<tr>
							<td>
							<textarea class="form-control" placeholder="글 내용" id="textAreaContent" name="bbsContent" maxlength="2048" style="height: 350px;"></textarea></td>														
						</tr>
					</tbody>
				</table>	
				<input type="submit" class="btn btn-primary pull-right" value="글쓰기" /> <br />
				<input type="file" name="uploadFile" id="uploadFile">
			</form>
		</div>
	</div>

</body>
</html>