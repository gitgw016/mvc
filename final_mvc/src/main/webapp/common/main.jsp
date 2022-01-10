<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"/>
<section>
	<h1>test - ${test}</h1>&nbsp;&nbsp;
	<h2>context - ${pageContext.request.contextPath}</h2>&nbsp;&nbsp;
	<h2>path - ${path}</h2>
</section>
<jsp:include page="footer.jsp"/>