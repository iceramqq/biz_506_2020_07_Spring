<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>나의 홈페이지</title>
<link href="${rootPath}/static/css/book-list.css?ver=1" rel="stylesheet">
<script src="${rootPath}/static/js/book-list.js?ver=1"></script>
</head>
<body>
<h3>보유도서리스트</h3>
<table id="book-list">
	<tr>
		<th>No</th>
		<th>도서명</th>
		<th>출판사</th>
		<th>저자</th>
		<th>가격</th>
		<th>구입일</th>
	</tr>
	<%
	// 서버로부터 books 데이터를 수신하여 데이터가 없으면 "데이터 없음" 이라고 표시
	// 있으면 tr,td 리스트를 만들어서 데이터 표시
	%>
	<c:choose>
		<c:when test="${empty BOOKS}">
			<tr>
				<td colspan="6">데이터가 없음</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${BOOKS}" var="book" varStatus="i">
				<tr>
					<td>${i.count}</td>
					<td class="book-title"
						data-seq="${book.seq}">${book.title}</td>
					<td>${book.author}</td>
					<td>${book.publisher}</td>
					<td>${book.price}</td>
					<td>${book.buydate}</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<div id="book-link-box">
	<a href="${rootPath}/books/input">새로작성</a>
</div>
</body>
</html>