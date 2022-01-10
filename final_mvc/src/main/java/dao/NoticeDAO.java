package dao;

import java.util.ArrayList;

import util.Criteria;
import vo.NoticeVO;

public interface NoticeDAO {

	// 게시글 삽입
	public boolean noticeWrite(NoticeVO notice);
	
	// 게시글 상세 보기
	public NoticeVO getNoticeVO(int notice_num);
	
	// 게시글 수정
	public boolean noticeUpdate(NoticeVO notice);
	
	// 게시글 삭제
	public boolean noticeDelete(int notice_num);
	
	// 게시글 목록
	public ArrayList<NoticeVO> getNoticeList(Criteria cri);
	
	// 전체 게시글 수
	public int getTotalCount(Criteria Cri);
	
}
