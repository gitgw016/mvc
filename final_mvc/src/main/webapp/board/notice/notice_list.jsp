<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../../common/header.jsp"/>
<section>
	<table>
		<tr>
			<th colspan="4">
				<h2>공지사항</h2>
			</th>
		</tr>
		<tr>
			<td colspan="4">
				<form action="noticeSearch.do" method="get">
					<select name="searchName">
						<option value="author">작성자</option>
						<option value="title">제목</option>
					</select>
					<input type="text" name="searchValue"/>
					<input type="submit" value="검색"/>
				</form>
			</td>
		</tr>
		<c:if test="${!empty member and member.id eq 'admin'}">
			<tr>
				<td colspan="4">
					<a href="noticeWriteForm.do">공지글 작성</a>
				</td>
			</tr>
		</c:if>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<c:choose>
		<c:when test="${!empty list}">
			<c:forEach var="board" items="${list}">
				<tr>
					<td>${board.notice_num}</td>
					<td><a href="noticeDetail.do?notice_num=${board.notice_num}">[${board.notice_category}] ${board.notice_title}</a></td>
					<td>${board.notice_author}</td>
					<td>${board.notice_date}</td>
				</tr>
			</c:forEach>
			<c:if test="${not empty pm}">
				<tr>
					<td colspan="4">
						<c:if test="${pm.start}">
							<a href="noticeSearch.do${pm.makeSearchQuery(1)}">[처음]</a>
						</c:if>
						<c:if test="${pm.prev}">
							<a href="noticeSearch.do${pm.makeSearchQuery(pm.startPage - 1)}">[이전]</a>
						</c:if>
						<c:forEach var="i" begin="${pm.startPage}" end="${pm.endPage}">
							<a href="noticeSearch.do${pm.makeSearchQuery(i)}">[${i}]</a>
						</c:forEach>
						<c:if test="${pm.next}">
							<a href="noticeSearch.do${pm.makeSearchQuery(pm.endPage + 1)}">[다음]</a>
						</c:if>
						<c:if test="${pm.last}">
							<a href="noticeSearch.do${pm.makeSearchQuery(pm.maxPage)}">[마지막]</a>
						</c:if>
					</td>
				</tr>
			</c:if>
		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="4">게시물 없음. ㅠㅠ</td>
			</tr>
		</c:otherwise>
		</c:choose>
	</table>
</section>
<jsp:include page="../../common/footer.jsp"/>
