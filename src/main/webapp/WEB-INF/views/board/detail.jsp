<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/text.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<h2>detail.jsp</h2>
	
	글번호: ${board.b_number}
	작성자: ${board.b_writer}
	제목: ${board.b_title}
	내용: ${board.b_contentsl}
	작성일자: ${board.b_date}
	조회수 : ${board.b_hits}
	파일:  <img alt="" src="/resources/upload/${board.b_filename}" >
	 ${board.b_filename}
	<a href="/board/delete?b_number=${board.b_number}">삭제</a>
	<a href="/board/update?b_number=${board.b_number}&page=${page}">수정</a>
	<a href="/board/paging?page=${page}">목록</a>
	
	<!-- 수정기능에 페이징 추가
		수정 처리 완료 후 상세페이지를 띄우고 거기서 목록링크를 클릭하면 직전 페이지로 돌아가도록 코드를 살짝 수정해봅시다. -->
	
	<!-- datail.jsp에서 수정, 삭제, 목록 링크를 각가 만들고 수정, 삭제기능을 구현해 봅시다
			1. 삭제기능: 삭제 클릭하면 삭제 처리하거 목록 출력
			2. 수정기능: 수정 클릭하면 수정화면(update.jsp)출력 후 제목, 내용만 수정하도록 하고 
						비밀번호 확인하여 맞으면 수정처리, 틀리면 alert 출력.
			           	수정처리 완료 후 detail.jsp 다시 출력할 것 -->
			           	
	<!-- 댓글작성 -->
	<div id="comment-write">
		<input type="hidden" id="b_number" value="${detail.b_number }">
		<input type="text" id="c_writer" value="${sessionScope.loginId}" readonly>
		<textarea type="text" id="c_contents"  name="c_contents" row="15px" placeholder="내용"></textarea>
		<button id="comment-write-btn">댓글등록</button>
	</div>	     
	
	<!-- 댓글목록출력 -->
	
	<div id="comment-list">
		<table class="table">
			<tr>
				<th>댓글번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성시간</th>
			</tr>
			<c:forEach items="${commentList }" var="comment">
			<tr>
				<th>${comment.c_number}</th>
				<th>${comment.c_writer}</th>
				<th>${comment.c_contents}</th>
				<th>${comment.c_date}</th>
			</tr>
			</c:forEach>
		</table>
	
	</div>
	      	
</body>
<script>
	/* const commentBtn = document.getElementById("comment-write-btn");
	commentBtn.addEventListener("click", function(){
		console.log("댓글동록버튼 클릭");
	}); */
	
	$("#comment-write-btn").click(function(){
		console.log("댓글등록버튼 클릭");
		/* ajax 문법을 이용하여 댓글 작성자, 작성내용을 comment/save 주소로 post 방식으로 전송하는 
		 	코드를 작성해 봅시다.
		*/
		// const commentWriter = document.getElementById("c_writer").value;
		// const commentContents = document.getElementById("c_contents").value;
		const commentWriter = $("#c_writer").val();
		const commentContents = $("#c_contents").val();
		const boardNumber = '${board.b_number}';
		console.log(commentWriter,commentContents,boardNumber);
		$.ajax({
			type: 'post',
			url: '/comment/save',
			data: {
				'c_writer': commentWriter,
				'c_contents': commentContents,
				'b_number': boardNumber},
			dataType: 'json',
			success: function(result){
				console.log(result);
				let output = "<table class='table'>";
				output += "<tr><th>댓글번호</th>";
				output += "<th>작성자</th>";
				output += "<th>내용</th>";
				output += "<th>작성시간</th></tr>";
				for(let i in result){
					output += "<tr>";
					output += "<td>"+result[i].c_number+"</td>";
					output += "<td>"+result[i].c_writer+"</td>";
					output += "<td>"+result[i].c_contents+"</td>";
					output += "<td>"+result[i].c_date+"</td>";
					output += "</tr>";
				}
				output += "</table>";
				// id가 comment-list인 div에 출력
				document.getElementById('comment-list').innerHTML = output;
				// 댓글 입력창을 비움. 
				document.getElementById('c_writer').value='';
				document.getElementById('c_contents').value='';
				
			},
			error: function(){
				console.log("흐름을 쓰윽 따라가보세요");
			}
			
			
		});
		
		
		
	});

</script>
</html>