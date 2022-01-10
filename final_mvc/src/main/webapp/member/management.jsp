<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"/>
<section>
	<table>
		<tr>
			<th colspan="6"><h2>회원 목록</h2></th>
		</tr>
		<tr>
			<th>회원번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>회원등록일</th>
		</tr>
		<!-- 회원 리스트 -->
		<c:choose>
			<c:when test="${!empty memberList}">
				<c:forEach var="m" items="${memberList}">
					<tr>
						<td>${m.num}</td>
						<td>${m.id}</td>
						<td>${m.name}</td>
						<td>${m.age}</td>
						<td>${m.gender == 'male' ? '남성' : '여성'}</td>
						<td>${m.regdate}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="6">
					<c:if test="${pageInfo.startPage > 1}">
						<a href="managementPage.mm?page=${pageInfo.startPage-1}">[이전]</a>
					</c:if>
						<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
							<a href="managementPage.mm?page=">[${i}]</a>
						</c:forEach>
					<c:if test="${pageInfo.endPage < pageInfo.maxPage}">
						<a href="managementPage.mm?page=${pageInfo.endPage+1}">[다음]</a>
					</c:if>
					</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="6">등록된 회원이 없다. ㅠㅠ</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</section>
<jsp:include page="../common/footer.jsp"/>