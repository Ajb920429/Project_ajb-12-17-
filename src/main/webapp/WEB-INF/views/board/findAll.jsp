<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
table, th, tr, td {
	border: 1px solid black;
}

img {
	width: 150px;
	height: 150px;
}
</style>
<script>
   function logout(){
      location.href="/member/logout"
   }
</script>
</head>
<body>
	<h2>글목록 findAll.jsp</h2>
	<a href="/">홈</a>
	<a href="/board/save">글쓰기</a>
	<button onclick="logout()">로그아웃</button>
	<a href="/member/page">마이페이지</a>
	
	<form action="/board/search" method="get">
	<select name="searchtype">
		<option value="b_title">제목</option>
		<option value="b_writer">작성자</option>
	</select>
	<input type="text" name="keyword" placeholder="검색어..">
	<input type="submit" value="검색">
	</form>
	
	<table class="table table-dark">
	<tr>
		<th>글번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성시간</th>
		<th>조회수</th>
		<th>삭제기능</th>
	</tr>
		<c:forEach items="${boardList}" var="board">
		<tr>
		<td>${board.b_number}</td>
		<td>${board.b_writer}</td>
		<td><a href="/board/detail?b_number=${board.b_number}&page=${paging.page}">${board.b_title}</a></td>
		<td>${board.b_date}</td>
		<td>${board.b_hits}</td>
		<c:choose>
						<c:when test="${sessionScope.loginId eq 'admin'}">
							<td><a href="delete?b_number=${b.b_number}">삭제</a></td>
						</c:when>
						
						<c:when test="${sessionScope.loginId eq b.b_writer}">
							<td><a href="delete?b_number=${b.b_number}">삭제</a></td>
							<td><a href="update?b_number=${b.b_number}">수정</a></td>
						</c:when>
					</c:choose>
		
		
		</tr>
		
		</c:forEach>
	</table>
	
	<div>
		<c:choose> 
			<c:when test="${paging.page<=1}">
				[이전]&nbsp;
			</c:when>
			<c:otherwise> 
			<!-- 현재 페이지에서 1감소한 페이지 요청하는 링크 -->
				<a href="/board/paging?page=${paging.page-1}">[이전]</a>&nbsp;
			</c:otherwise>
		</c:choose>
		
		<!-- for(int i=startPage; i<=endPage; i++ -->
		
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			<c:choose>
			
				<c:when test="${i eq paging.page}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="/board/paging?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="/board/paging?page=${paging.page+1}">[다음]</a>
			</c:otherwise>
		</c:choose>
	</div>
	
	
	
</body>
</html>