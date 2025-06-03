<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>upload Form.....</h1>
	
	<!-- enctype의 기본값 : application/x-www-form-urlencoded // 이미지 파일은 반드시 multipart/form-data 기재 필요-->
	<!-- multiple="multiple" : 여러개 파일 등록 시 기재 -->
	<form action="uploadForm" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" multiple="multiple">
		<button>Submit</button>
	</form>
</body>
</html>