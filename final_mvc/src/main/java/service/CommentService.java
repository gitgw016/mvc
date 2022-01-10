package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface CommentService {

	// 댓글 작성
	public boolean insertComment(HttpServletRequest request);
	
	// 댓글 목록
	public Map<String, Object> getCommentList(HttpServletRequest request);
	
	// 댓글 삭제
	public boolean deleteComment(HttpServletRequest request);
}







