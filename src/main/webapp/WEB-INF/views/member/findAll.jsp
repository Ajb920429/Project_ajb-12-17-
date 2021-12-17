<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	function detailAjax(m_number){
		console.log(m_number);
		const view = document.getElementById('detail-view');
		$.ajax({
			type: 'post',
			url: 'detailAjax',
			data: {'m_number': m_number},
			dataType: 'json',
			success: function(result){ // 처리결과를 받는 부분
				console.log(result);
				console.log(result.m_number);
				console.log(result.m_id);
				
				let output = "<table class='table'>";
				output += "<tr><th>number</th> <th>id</th> <th>password</th> <th>name</th>";
				output += "<th>email</th> <th>phone</th> </tr>";
				output += "<tr>";
				output += "<td>"+result.m_number+"</td>";
				output += "<td>"+result.m_id+"</td>";
				output += "<td>"+result.m_password+"</td>";
				output += "<td>"+result.m_name+"</td>";
				output += "<td>"+result.m_email+"</td>";
				output += "<td>"+result.m_phone+"</td>";
				output += "</tr>";
				output += "</table>";
				
				view.innerHTML = output;
				
				
			},
			error: function(){
				console.log('오타를 찾으세요')
			}
			
		})
	}
	
</script>
</head>
<body>
	<h2>회원목록 findAll.jsp</h2>
	<table class="table table-dark">
	<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>이메일</th>
		<th>전화번호</th>
		<th>상세조회</th>
		<th>상세조회()</th>
		<th>삭제</th>
		<th>수정</th>
	</tr>
		<c:forEach items="${memberList}" var="member">
		<tr>
		<td>${member.m_number}</td>
		<td>${member.m_id}</td>
		<td>${member.m_password}</td>
		<td>${member.m_name}</td>
		<td>${member.m_email}</td>
		<td>${member.m_phone}</td>
		<!-- detail 이라는 주소요청을 통해 상세조회를 해봅시다. 상세조회 데이터는 detail.jsp에 출력합니다. -->
		<td><a href="detail?m_number=${member.m_number}">조회</a></td>
		<td><button onclick="detailAjax('${member.m_number}')">조회(ajax)</button>
		<!-- 삭제를 처리하고 다시 리스트를 출력함(당연히 사게 테이터는 보이지 않아야 함) -->
		<td><a href="delete?m_number=${member.m_number}">삭제</a></td>
		<!-- 1. 수정화면 요청
				수정화면을 요청하면 DB로부터 해당 회원의 정보를 가져와서 update.jsp 에 출력함(form 태그 사용)
			 2. 수정처리
			 	이메일, 전화번호만 수정할 수 있음.
			 	update.jsp에 입력한 내용을 서러로 전달하여 수정을 처리하고 해당 회원의detail 페이지를 출력할 것-->
		<td><a href="update?m_number=${member.m_number}">수정</a></td>
		</tr>
	</c:forEach>
	</table>
	<!-- ajx로 가져온 상세조회 결과를 여기에 보여줌 -->
	<div id="detail-view"></div>
	
</body>
</html>