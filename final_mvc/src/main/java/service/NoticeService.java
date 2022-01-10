package service;

import javax.servlet.http.HttpServletRequest;

public interface NoticeService {

	// 게시물 작성
	public boolean noticeWrite(HttpServletRequest request);
	
	// 게시물 수정
	public boolean noticeUpdate(HttpServletRequest request);
	
	// 게시물 삭제
	public boolean noticeDelete(HttpServletRequest request);
	
	// 게시글 상세보기
	public void noticeDetail(HttpServletRequest request);
	
	// 게시글 검색 list
	public void noticeSearch(HttpServletRequest request);
}
