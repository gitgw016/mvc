<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- common/comment.jsp -->
<style>
	.commentWrap{
		width:100%;
		border:1px solid #ddd;
		padding:15px;
		margin-top:15px;
		margin-bottom:10px;
	}
	
	.commentWrap .commentWrite textarea.comment_content{
		border:none;
		resize:none;
		outline:0;
		font-size:1.1em;
		color:#333;
		float:left;
		height:70px;
		padding:5px;
		width:85%;
	}
	
	.commentWrap .commentWrite{
		border:1px solid #ccc;
		overflow:hidden;
	}
	
	.commentWrite input[type='submit']{
		float:right;
		width:13%;
		height:80px;
		font-weight:bold;
	}
	
	/* 댓글 리스트 */
	.commentListWrap{
		width:100%;
		border:1px solid #ddd;
		padding:15px;
		margin-top:15px;
	}
	
	.commentListWrap textarea{
		border:none;
		resize:none;
		outline:0;
		font-size:1.3em;
		color:#333;
		width:100%;
	}
	
	.closeBtn{
		float:right;
		border:1px solid #ccc;
		padding:5px;
	}
	
	.closeBtn input{
		border:none;
		outline:none;
		background:none;
	}
	
	.closeBtn:hover input{
		cursor:pointer;
	}
	
	/* 페이징 블럭 */
	.pagingWrap{
		width:100%;
		text-align:center;
		margin-top:15px;
		margin-bottom:50px;
	}
	
	a{
		text-decoration:none;
		color:black;
	}
	
	a:hover{
		color:#ccc;
	}
	
</style>
<c:if test="${!empty member}">
	<div class="commentWrap">
		<h3>댓글 작성</h3>
		<br/>
		<div class="commentWrite">
			<form action="commentWrite.bo" method="POST"> 
				<input type="hidden" name="id" value="${member.id}"/>
				<input type="hidden" name="name" value="${member.name}"/>
				<input type="hidden" name="qna_num" value="${boardVO.qna_num}"/>
				<textarea name="comment_content" class="comment_content" required></textarea>
				<input type="submit" value="등록" />
			</form>
		</div>
	</div>
</c:if>
<!-- hm(Map) -->
<!-- 게시글 상세 : boardVO -->
<!-- 댓글 리스트 : hm.list -->
<!-- 댓글 페이징 : hm.pm -->
<c:if test="${!empty hm.list}">
	<br/>
	<h3>댓글 목록[${hm.pm.totalCount}]</h3>
	<c:forEach var="cmt" items="${hm.list}">
		<div class="commentListWrap">
			<c:choose>
			<c:when test="${cmt.comment_delete eq 'N'}">
				<c:if test="${!empty member && member.id eq cmt.comment_id}">
					<form action="commentDelete.bo" method="POST">
						<input type="hidden" name="id" value="${member.id}" />
						<input type="hidden" name="comment_num" value="${cmt.comment_num}" />
						<input type="hidden" name="qna_num" value="${boardVO.qna_num}" />
						<div class="closeBtn">
							<input type="submit" value="X" title="댓글 삭제"/>
						</div>
					</form>
				</c:if>
				<div>
					${cmt.comment_name}&nbsp;&nbsp;|&nbsp;&nbsp;${cmt.comment_date}
				</div>
				<div>
					<textarea readonly>${cmt.comment_content}</textarea>
				</div>
			</c:when>
			<c:otherwise>
				<h3>삭제된 댓글입니다.</h3>
			</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
	<div class="pagingWrap">
		<c:forEach var="i" begin="${hm.pm.startPage}" 
		end="${hm.pm.endPage}">
			<a href="boardRead.bo?page=${i}&qna_num=${boardVO.qna_num}">[${i}]</a>
		</c:forEach>	
	</div>
</c:if>















