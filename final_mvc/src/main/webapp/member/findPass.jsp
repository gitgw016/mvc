<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../common/header.jsp"/>
<style>
	.loadingWrap{
		display:none;
		position:absolute;
		width:100%;
		height:100%;
		background-color:rgba(0,0,0,0.7);
		top:0;
		left:0;
		text-align:center;
	}
	
	.contentWrap{
		border:1px solid white;
		position:absolute;
		width:300px;
		height:150px;
		padding:15px;
		top:50%;
		left:50%;
		margin-top:-75px;
		margin-left:-150px;
		color:white;
	}
	
	.contentWrap img{
		width:30px;
		
	}
</style>
<section>
	<form action="findPassSubmit.mc" method="post">
		<table>
			<tr>
				<th colspan=2>
					<h2>비밀번호 찾기</h2>
				</th>
			</tr>
			<tr>
				<td>id(email)</td>
				<td><input type="email" name="id" required/></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" required/></td>
			</tr>
			<tr>
				<td><input type="submit" id="btn" value="확인"/></td>
			</tr>
		</table>
	</form>
	<div id="mailLoading" class="loadingWrap">
		<div class="contentWrap">
			<h1>메일 전송중...</h1>
			<br/><br/>
			<img src="${path}/resources/img/loading.gif"/>
		</div>
	</div>
</section>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(){
		$("#btn").click(function(){
			$("#mailLoading").css("display","block");
			$(".contentWrap img").animate({
				width:90
			},10000);
		});
	});
</script>
<jsp:include page="../common/footer.jsp"/>