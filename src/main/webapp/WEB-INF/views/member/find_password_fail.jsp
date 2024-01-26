<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 실패</title>
<link href="<c:url value='/resources/css/member/find_password_result.css'/>" rel="stylesheet" type="text/css">
</head>
<body>
   <jsp:include page="../include/header.jsp"/>
   <jsp:include page="../include/nav.jsp"/>   
   <section>
      <div id="section_wrap">
         <div class="word">
            <h3>새 비밀번호 생성에 실패했습니다.</h3>
         </div>
         <div class="others">
            <a href="<c:url value='/member/findPassword'/>">비밀번호 찾기</a>
            <a href="<c:url value='/member/login' />">로그인</a>
         </div>
      </div>
   </section>
</body>
</html>