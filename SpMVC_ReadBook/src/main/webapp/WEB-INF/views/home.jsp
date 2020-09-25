<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />        
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link href="${rootPath}/static/css/index.css" rel="stylesheet" />
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script>
    	// js파일에서 el tag의 ${rootPath} 값을 참조하기 위해서
    	// rootPath 변수를 전역으로 선언해 둔다
    	var rootPath = "${rootPath}"
    </script>
    <script src="${rootPath}/static/js/main-nav.js?ver=2020-09-25-2"></script>
  </head>
  <body>
    <header>
      <h1>Read Book 2020</h1>
      <h5>책속에 길이 있다...</h5>
    </header>
    <nav id="main-nav">
      <ul>
        <li id="menu_home">Read Book</li>
        <li id="menu_books">도서정보</li>
        <li id="menu_read-book">독서록</li>
        <li>네이버 검색</li>
        <li id="menu_join">회원가입</li>
        <li id="menu_login">로그인</li>
        <li id="menu_mypage">마이페이지</li>
        <li id="menu_logout">로그아웃</li>
      </ul>
    </nav>
    <section id="main-section">
    	<c:choose>
		    <c:when test="${BODY == 'BOOK-LIST'}">
		    	<%@ include file="/WEB-INF/views/books/book-list.jsp" %>
		    </c:when>
		    <c:when test="${BODY == 'BOOK-WRITE'}">
		    	<%@ include file="/WEB-INF/views/books/book-write.jsp" %>
		    </c:when>
		    <c:when test="${BODY == 'BOOK-DETAIL'}">
		    	<%@ include file="/WEB-INF/views/books/book-detail.jsp" %>
		    </c:when>
		    <c:otherwise>
		    	<%@ include file="/WEB-INF/views/main-body.jsp" %>
		    </c:otherwise>
	    </c:choose>
    </section>
    <footer>
      <address>CopyReght &copy; iceram22@naver.com</address>
    </footer>
  </body>
</html>
