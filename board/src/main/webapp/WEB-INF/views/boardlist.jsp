<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/> <!-- root/board (http://localhost:8090/board) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판목록</title>
<style type="text/css">
h3,h5 { text-align: center; }
table { margin: auto; width: 800px }
#tr_top { background: orange; text-align: center; }
#emptyArea { margin: auto; width: 800px; text-align: center; }
#emptyArea a {
	display: inline-block;
	width: 20px;
	height: 20px;
	text-decoration: none;
}
#emptyArea .btn {
	background: lightgray;
}
#emptyArea .select {
	background: lightblue;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	function callBtn(num) {
		var type = $("#type").val();
		var keyword = $("#keyword").val();
		// 검색 옵션이 선택되어 있고, 입력값이 null이랑 공백이 아니면
		// 해당 callBtn을 호출한 <a> 태그의 기능을 없애고 (return false를 통해 기능을 없앰)
		// 검색 옵션을 유지한 채로 페이지 이동 (검색 옵션과 이동할 페이지 번호를 가진 채로 검색 폼 submit)
		if(type!='all' && keyword!=null && keyword.trim()!='') {
			$('#page').val(num);
			$('#searchform').submit();
			// onClick에 false를 반환하는 경우, 기존 기능을 없앰
			return false;
		}
		// if문이 아닐 경우, true
	}
</script>
</head>
<body>

<h3>글 목록<br/>
<c:if test="${user ne Empty}">
	<a href="${contextPath}/boardwrite">글쓰기</a>&nbsp;&nbsp;
</c:if>
<a href="${contextPath}/main">메인</a>

</h3>

<form action="${contextPath}/boardsearch" method="post" id="searchform">			
	<input type="hidden" name="page" id="page" value="1">		
<h5>
	<select name="type">
		<option value="all">선택</option>
		<option value="subject" ${type eq 'subject'? 'selected':''}>제목</option>
		<option value="writer" ${type eq 'writer'? 'selected':''}>작성자</option>
		<option value="content" ${type eq 'content'? 'selected':''}>내용</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${keyword}"/>
	<input type="submit" value="검색"/>
</h5>
</form>					
		<table>
			<tr id="tr_top"><th>번호</th><th>제목</th><th>작성자</th><th>날짜</th><th>조회수</th><th>삭제</th></tr>
			<c:forEach items="${boardList}" var="board">
				<tr>
					<td>${board.num }</td>
					<td><a href="${contextPath}/boarddetail/${board.num}/${pageInfo.curPage}">${board.subject }</a></td>
					<td>${board.writer}</td>
					<td>${board.writedate}</td>
					<td>${board.viewcount}</td>
					<td>
					<c:if test="${user.id == board.writer}">
						<!-- root부터 시작하는 contextPath를 통해 모든 경로를 새로 설정 -->
						<a href="${contextPath}/boarddelete/${board.num}/${pageInfo.curPage}">삭제</a>
					</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<div id="emptyArea">
			<c:choose>  
				<c:when test="${pageInfo.curPage>1}">
					<a href="${contextPath}/boardlist/${pageInfo.curPage-1}" onClick="return callBtn(${pageInfo.curPage-1});">&lt;</a>
				</c:when>
				<c:otherwise>
					&lt;
				</c:otherwise>
			</c:choose>
			&nbsp;&nbsp;

			<c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="i">
				<c:choose>
					<c:when test="${pageInfo.curPage==i}">
						<a href="${contextPath}/boardlist/${i}" class="select" onclick="return callBtn(${i});">${i}</a>&nbsp;
					</c:when>
					<c:otherwise>
						<a href="${contextPath}/boardlist/${i}" class="btn" onclick="return callBtn(${i});">${i}</a>&nbsp;
					</c:otherwise>
					
				</c:choose>
				
			</c:forEach>

			<c:choose>  
				<c:when test="${pageInfo.curPage < pageInfo.allPage}">
					<a href="${contextPath}/boardlist/${pageInfo.curPage+1}" onClick="return callBtn(${pageInfo.curPage+1});">&gt;</a>
				</c:when>
				<c:otherwise>
					&gt;
				</c:otherwise>
			</c:choose>
			&nbsp;&nbsp;
		</div>
</body>
</html>