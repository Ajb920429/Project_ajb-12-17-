<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	input{
	display: block;
	}
</style>
	

<script >
	 /* 비밀번호 입력창에서 비밀번호를 입력받고 DB에서 가져온 비밀번호와 비교해서 일치하면 update처리 일치하지 않으면 비밀번호가 일치하지 않습니다 alert출력 */
		function update(){
		 // 이 문장이 아래 form을 전송하는 문장
		 console.log('update 함수호출')
		
		 const pw = document.querySelector("#b_password").value;
		 console.log(pw);
		 const pwDB = '${board.b_password}';
		 console.log(pwDB);
		 if(pw==pwDB){
			 update_form.submit();
		 }else{
			 alert('비밀번호가 틀립니다.');
		 }
	 }

</script>
</head>
<body>
	<h2>update.jsp</h2>
	<form action="/board/update" method="post" name="update_form">
		<input type ="hidden" name="page" value=${page}><br>
		글번호: <input type="text" name="b_number" value="${board.b_number}" readonly><br>
		작성자: <input type="text" name="b_writer" value="${board.b_writer}" readonly><br>
		글비밀번호: <input type="password" name="b_password" id="b_password"><br>
		제목: <input type="text" name="b_title" value="${board.b_title}"><br>
		내용: <textarea name= "b_contentsl" rows="5">"${board.b_contentsl}"</textarea><br>
		<input type="button" value="글수정" onclick="update()">
		</form>
	
</body>
</html>