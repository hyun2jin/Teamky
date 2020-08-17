<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="board.action.BoardModel" %>
<%@ page import="project.model.Member" %>
<%@ page import="guest.action.GuestModel" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script type="text/css">

function checkValue()
{
	if(!document.guestbookInfo.guestid.value) {
		alert("이름을 입력하세요");
		return false;
	}
	
	if(!document.guestbookInfo.guestpass.value) {
		alert("암호을 입력하세요");
		return false;
	}
	
	if(!document.guestbookInfo.guestcont.value) {
		alert("암호을 입력하세요");
		return false;
	}	
}

#wrap {
    width: 700px;
    margin: 0 auto 0 auto;
}

#comment{
    text-align :left;
}

#writeGestFrom, #listGestFrom{
    text-align :center;
}

</script>

<% String cm = null; %>

<script type="text/javascript">



function switchStatus()
{
	  let switchButton = document.getElementById('chimg');
	  let img = document.getElementById('ledPic');
	  if(switchButton.value == "/images/icon/11.gif") {
	    img.src = "/images/icon/11.gif";
	  }
	  if(switchButton.value == "/images/icon/12.gif") {
		  img.src = "/images/icon/12.gif";
	  } 
	  if(switchButton.value == "/images/icon/13.gif") {
	  	  img.src = "/images/icon/13.gif";
      } 
      if(switchButton.value == "/images/icon/14.gif") { 
    	  img.src = "/images/icon/14.gif";	  
	  }
      if(switchButton.value == "/images/icon/15.gif") { 
    	  img.src = "/images/icon/15.gif";	  
	  }
      if(switchButton.value == "/images/icon/16.gif") { 
    	  img.src = "/images/icon/16.gif";	  
	  }
      if(switchButton.value == "/images/icon/17.gif") { 
    	  img.src = "/images/icon/17.gif";	  
	  }
      if(switchButton.value == "/images/icon/18.gif") { 
    	  img.src = "/images/icon/18.gif";	  
	  }
      if(switchButton.value == "/images/icon/19.gif") { 
    	  img.src = "/images/icon/19.gif";	  
	  }      
      if(switchButton.value == "/images/icon/20.gif") { 
    	  img.src = "/images/icon/20.gif";	  
	  }	
}



</script>

<style>
  th, td {
    border1: 1px solid #444444;
  }
</style>


 <title>방명록</title>
</head>
<body>


<%
	Member mem = (Member)session.getAttribute("userinfo");

	String userid = null;
	String ip = request.getRemoteHost();
	
	if(mem != null)
		userid = mem.getMem_nick();
	
	String i1 = "/images/icon/11.gif";
	String i2 = "/images/icon/12.gif";
	String i3 = "/images/icon/13.gif";
	String i4 = "/images/icon/14.gif";
	String i5 = "/images/icon/15.gif";
	String i6 = "/images/icon/16.gif";
	String i7 = "/images/icon/17.gif";
	String i8 = "/images/icon/18.gif";
	String i9 = "/images/icon/19.gif";
	String i10 = "/images/icon/20.gif";	
%>



 
<jsp:include page="/WEB-INF/menu/top.jsp" flush="false"></jsp:include>
<jsp:include page="/WEB-INF/menu/menu.jsp" flush="false"></jsp:include>
    
    
    
    
    <br>
    <b><font size="6" color="gray">방명록</font></b>
    <hr size="1" width="700">
    <br>
    
	<div id="wrap">     
	    <!-- 글 등록 부분 시작-->
	    
	    <%if(mem != null) { %>
	    
	    <div id="writeGestFrom">
	        <form name="gestbookInfo" method="post" action="guestbook.do"
	            onsubmit="return checkValue()" >
	            <table width="700">
	                <tr>
	                	<th></th><th></th><th></th><th></th><th></th>
	                </tr>
	                <tr>
	                    <td>이름</td>
	                    <td><input type="text" name="guestbook_id" readonly value="<%=userid%>"></td>
	                    <td>비밀번호</td>
	                    <td><input type="password" name="guestbook_password"></td>
	                    <td>
							<select id="chimg" name="selimage" onchange="switchStatus()" style="width: 100px;">
		       				<option value=<%=i1%>>1</option>
		       				<option value=<%=i2%> selected="selected">2</option>
		       				<option value=<%=i3%>>3</option>
		       				<option value=<%=i4%>>4</option>
		       				<option value=<%=i5%>>5</option>
		       				<option value=<%=i6%>>6</option>
		       				<option value=<%=i7%>>7</option>
		       				<option value=<%=i8%>>8</option>
		       				<option value=<%=i9%>>9</option>
		       				<option value=<%=i10%>>10</option>
   							</select><br />
	                        <img id="ledPic" src="/images/icon/12.gif" >	                                        	                    	
	                    </td>	                    
	                </tr>
	                <tr>
	                    <td colspan="5">
	                        <textarea rows="7" cols="80" name="guestbook_content"></textarea>
	                    </td>
	                </tr>	 
	                	                           	
	            </table>
	            <br>            
 				
	            <input type="submit" class="btn btn-primary" value="등록">
	        </form>
	    </div>
	     <% } else { %>
	     	<p>로그인후 이용가능합니다</p>
	     <% } %>
    
	    <!-- 글 등록 부분 끝-->
	 	<jsp:include page="guestlist.jsp" flush="false"></jsp:include>
	    
	 
 	</div>

</body>
</html>