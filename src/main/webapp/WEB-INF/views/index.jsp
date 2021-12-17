<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
   a{
      display: block
   }
</style>
<script>
   function logout(){
      location.href="/member/logout"
   }
</script>

</head>
<body>
<h2>index.jsp</h2>
   <a href="/member/save">회원가입</a>
   <a href="/member/login">로그인</a>
   <a href="/board/save">글쓰기</a><br>
<c:choose>
   <c:when test="${sessionScope.loginId eq 'admin'}">
   <button onclick="logout()">로그아웃</button>
      <a href="/member/findAll">전체회원목록</a>
      <a href="/board/paging?page=${page}">글목록</a><br>
   </c:when>
   
   <c:when test="${sessionScope.loginId != null }"> 
   <button onclick="logout()">로그아웃</button>
      <a href="/member/mypage?m_number=${loginNumber}">MyPage</a>
      <a href="/board/paging?page=${page}">글목록</a>
      
     
   </c:when>
</c:choose>
   
회원: ${sessionScope.loginId}

</body>
</html>