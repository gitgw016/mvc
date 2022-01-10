<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"/>
<section>
	<form action="updateSubmit.mc" method="post">
		<input type="hidden" name="num" value="${member.num}"/>
		<table>
			<tr>
				<th colspan=2>
					<h2>회원 정보 수정</h2>
				</th>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input readonly type="email" name="id" placeholder="email" value="${member.id}" required/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pass" value="${member.pass}" required/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${member.name}" required/></td>
			</tr>
			<tr>
				<td>나이</td>
				<td><input type="number" name="age" value="${member.age}" required/></td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<c:choose>
						<c:when test="${member.gender eq 'male'}">
							<input type="radio" name="gender" value="male" checked/>남성 <input type="radio" name="gender" value="female" />여성
						</c:when>
						<c:otherwise>
							<input type="radio" name="gender" value="male"/>남성 <input type="radio" name="gender" value="female" checked/>여성
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th colspan=2><input type="submit" value="정보수정"/></th>
			</tr>
		</table>
	</form>
</section>
<jsp:include page="../common/footer.jsp"/>