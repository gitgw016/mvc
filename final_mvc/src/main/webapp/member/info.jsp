<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"/>
<section>
		<table border=1>
			<tr>
				<th colspan=2>
					<h2>회원 정보</h2>
				</th>
			</tr>
			<tr>
				<td>회원번호</td>
				<td>${member.num}</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td>${member.id}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${member.name}</td>
			</tr>
			<tr>
				<td>나이</td>
				<td>${member.age}</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<c:choose>
						<c:when test="${member.gender eq 'male'}">남성</c:when>
						<c:otherwise>여성</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>회원 가입일</td>
				<td>${member.regdate}</td>
			</tr>
			<tr>
				<td>회원정보 수정일</td>
				<td>${member.updatedate}</td>
			</tr>
			<tr>
				<th colspan=2>
				<input onclick="location.href='test';" type="button" value="메인"/>
				<input onclick="location.href='update.mc';" type="button" value="정보수정"/>
				<input onclick="location.href='withdraw.mc';" type="button" value="회원탈퇴"/>
				</th>
			</tr>
		</table>
</section>
<jsp:include page="../common/footer.jsp"/>