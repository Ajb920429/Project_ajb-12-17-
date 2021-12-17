<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
input {
   display: block;
}

</style>
</head>
<body>
   <h2>MyPage</h2>
   <a href="/">홈</a>
   <table>
   <thead>
      <tr>
         <th>번호</th>
         <th>아이디</th>
         <th>비밀번호</th>
         <th>이름</th>
         <th>이메일</th>
      </tr>
   </thead>
   <tbody>
      <tr>
         <td>${mypage.m_number}</td>
         <td>${mypage.m_id}</td>
         <td>${mypage.m_pw}</td>
         <td>${mypage.m_name}</td>
         <td>${mypage.m_email}</td>
         <td><a href="/member/update?m_number=${mypage.m_number}">수정</a>
      </tr>
   </tbody>
   </table>
</body>
</html>