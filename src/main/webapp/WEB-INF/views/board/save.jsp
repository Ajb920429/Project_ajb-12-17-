<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input{
		display: block;}
	textarea{
		display: block;}
		
</style>
</head>
<body>
	<h2>글쓰기페이지</h2>
	<form action="/board/save" method="post">
		작성자: <input type="text" name="b_writer"><br>
		비밀번호: <input type="password" name="b_password"><br>
		제목: <input type="text" name="b_title"><br>
		내용: <textarea name= "b_contentsl" rows="5"></textarea><br>
		<input type="submit" value="글작성">
		</form>
		
		<h2>파일첨부 글쓰기</h2>
	<form action="/board/savefile" method="post" enctype="multipart/form-data">
		작성자: <input type="text" name="b_writer"><br>
		비밀번호: <input type="password" name="b_password"><br>
		제목: <input type="text" name="b_title"><br>
		내용: <textarea name= "b_contentsl" rows="5"></textarea><br>
		파일: <input type="file" name="b_file"><br>
		<input type="submit" value="글작성">
		</form>
</body>
</html>