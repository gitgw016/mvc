package dao;

import java.util.ArrayList;

import util.Criteria;
import vo.BoardVO;

public interface QNABoardDAO {
	
	// 전체 게시물 수
	public int getTotalCount();
	
	// 게시물 목록
	public ArrayList<BoardVO> getBoardList(Criteria cri);
	
	// 게시물 작성 요청
	public void boardWrite(BoardVO vo);
	
	// 게시물 한개의 정보 요청
	public BoardVO getBoardVO(int qna_num);
	
	// 조회수 증가
	public void updateReadCount(int qna_num);
	
	// 답변글 작성
	public void boardReplySubmit(BoardVO board);
	
	// 게시글 수정
	public boolean boardUpdate(BoardVO vo);
	
	// 게시글 삭제
	public boolean boardDelete(int qna_num, int qna_writer_num);
}
