<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />

<script>
	document.addEventListener("DOMContentLoaded", function() {
		// table 의 tr.st_tr에 이벤트 핸들링 설정
		document.querySelector("table.station-list").addEventListener("click", function(e) {
			const busstop_id = e.target.closest("TR").dataset.id
			const data = {
				station : busstop_id
			}
			fetch("${rootPath}/bis/busstop",
					{
						method : 'POST',
						headers : {"content-Type" : "application/json"},
						body : JSON.stringify(data)
					},
					
			)
			.then(function(result){
				// fetch를 사용하여
				//
				return result.json()
			})
			.then(function(resData){
				// resData는 도착정보 리스트가 담긴 상태이다.
				console.log(resData)
				// js 코드를 이용하여 데이터를 html tag로 생성하고 
				// 도착정보 view에 보이기를 하기
				
				const stopTitleList = [
					"LINE_NAME",
					"BUSSTOP_NAME",
					"REMAIN_MIN",
					"REMAIN_STOP"
				]
				const tr_list = resData.map(function(bus){
					const td_list = stopTitleList.map(function(title){
						const td = document.createElement("TD")
						td.textContent = bus[title]
						return td
					})
					const tr = document.createElement("TR")
					tr.append(...td_list)
					return tr
				})
				document.querySelector("table.busstop-list tbody").innerHTML = ""
				document.querySelector("table.busstop-list tbody").append(...tr_list)
			})
			.catch(function(error){
				alert("서버 통신오류")
			})
		})
	})
</script>
<article class="station-list">
	<table class="bis station-list">
		<thead>
			<tr>
				<th>정류소ID</th>
				<th>정류소명</th>
				<th>다음정류소</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty ST_LIST }">
					<tr>
						<td colspan="3">데이터가 없음</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${ST_LIST}" var="st">
						<tr data-id="${st.BUSSTOP_ID}" class="st_tr">
							<td>${st.STATION_NUM}</td>
							<td>${st.BUSSTOP_NAME}</td>
							<td>${st.NEXT_BUSSTOP}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</article>