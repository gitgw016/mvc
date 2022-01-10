<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"/>
<section>
	<form action="withDrawSubmit.mc" method="post">
	<table>
		<tr>
			<th colspan=2><h2>회원 탈퇴 - 비밀번호 확인</h2></th>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="tempPass" required/></td>
		</tr>
		<tr>
			<td colspan=2><button>회원탈퇴</button></td>
		</tr>
	</table>
	</form>
</section>
<jsp:include page="../common/footer.jsp"/>