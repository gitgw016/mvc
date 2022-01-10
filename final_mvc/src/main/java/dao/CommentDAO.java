package dao;

import java.util.ArrayList;

import util.Criteria;
import vo.CommentVO;

public interface CommentDAO {
	// comment 등록
	public boolean insertComment(CommentVO vo); 
	
	// 해당 게시물의 전체 comment 개수
	public int getCommentTotalCount(int qna_num);
	
	// 해당 게시물의 comment 목록
	public ArrayList<CommentVO> getCommentList(int qna_num,Criteria cri);
	
	// comment 삭제 요청
	public boolean deleteComment(int comment_num, String id);
}







