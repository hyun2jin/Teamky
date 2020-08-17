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
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
  
<title>Insert title here</title>
</head>

<style>

#div_con{
	width:100%;
	height:auto;
	background-color: white;
	text-align: left;
	border:thin;
}

</style>

<script>
$('#myForm').submit(function() {
	var markupStr = $('#summernote').summernote('code');
});
}
</script>


<body>




<div style="border: 1px; float: left; width: 90%">
	<div id="div_con">
 	<form action="write.do" method="post" id="myForm">
	  <textarea id="summernote" name="editordata" style="display:none"></textarea>
	   <script>
	    $(document).ready(function() {
	    	$('#summernote').summernote({
	  		  height: 600,                 // 에디터 높이
	  		  minHeight: null,             // 최소 높이
	  		  maxHeight: null,             // 최대 높이
	  		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
	  		  lang: "ko-KR",					// 한글 설정
	  		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정	            
	  	});
	    });
	  </script>
	  <input type="submit" class="btn btn-primary pull-right" value="등록" />
	 </form>	 
	 </div>
 </div>
 
 
</body>
</html>