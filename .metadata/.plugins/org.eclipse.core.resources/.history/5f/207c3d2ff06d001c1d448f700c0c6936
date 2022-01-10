<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath}" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
<link href="${path}/resources/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${path}/resources/css/header.css" rel="stylesheet" type="text/css"/>
<link href="${path}/resources/css/footer.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
	<div>
		<ul>
			<li><a href="test">HOME</a></li>
			<c:choose>
				<c:when test="${!empty sessionScope.member}">
					<li><a href="info.mc">${sessionScope.member.name}</a></li>
					<c:if test="${member.id eq 'admin'}">
					<li><a href="managementPage.mm">회원관리</a></li>
					</c:if>
					<li><a href="logOut.mc">로그아웃</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="login.mc">로그인</a></li>
					<li><a href="join.mc">회원가입</a></li>
				</c:otherwise>
			</c:choose>
					<li><a href="mailTest">Google SMTP Test</a></li>
		</ul>
	</div>
	<div>
		<ul>
			<li><a href="noticeSearch.do">공지사항</a></li>
			<li><a href="boardList.bo">질문과 답변</a></li>
			<li><a href="javascript.test">javascript</a></li>
			<li><a href="jQuery.test">jQuery</a></li>
		</ul>
	</div>
</header>
