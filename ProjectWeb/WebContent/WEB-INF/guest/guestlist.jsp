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
	Member mem = (Member)session.getAttribute("userinfo");

	String userid = null;
	
	if(mem != null)
		userid = mem.getMem_nick();
	
	ArrayList<GuestModel>	list = (ArrayList)session.getAttribute("guestlist");	
%>

 	  <div class="container" style="border: 1px; float: left; width: 90%; height:500px">
	    <%
	    	if(list != null && list.size() > 0) {
	    %><table class="table table-striped" style="text-align:center; border:1px solid #dddddd"><br />
			<thead>
				<tr>
					<th style="background-color: #eeeeee; text-align: center;">번호</th>
					<th style="background-color: #eeeeee; text-align: center;">작성자 </th>
					<th style="background-color: #eeeeee; text-align: center;">내용</th>				
				</tr>
			</thead>	    	
	    	 <%
	    	for(int i=0;i<list.size();i++) {
	    		GuestModel gm = list.get(i);	    		
	    	%>
	    	<tr>
	    	<td width="10%"><%=gm.getGuestNo() %>
	    	<br />
	    	
	    	<%
				//글작성자 본인일시 수정 삭제 가능
				if(userid != null && userid.equals(gm.getGuestNick())){
			%>
				<a href="guestdelete.do?delnum=<%=gm.getGuestNo()%>" style="float: center">[삭제]</a>
			<%
				}
			%>

	    	</td>
	    	<td width="20%"><img src="<%=gm.getGuestImage() %>"/><br />[<%=gm.getGuestNick() %>]</td>
	    	<td width="60%"><%=gm.getGuestCont() %></td>
    	
	    	</tr>
	    <%  } } %>
	    </table>
	    </div>


</body>
</html>