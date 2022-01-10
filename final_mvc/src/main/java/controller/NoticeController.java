package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ManagementService;
import service.MemberService;
import service.NoticeService;
import service.NoticeServiceImpl;

@WebServlet("*.do")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NoticeService ns = new NoticeServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// cookie 자동 로그인
		MemberService.loginCheck(request);
		
		String command = request.getRequestURI().substring(request.getContextPath().length()+1);
		// /contextPath/****.do
		// 요청 성공 시
		String success = "/board/notice/notice_success.jsp";
		// 요청 실패 시
		String failed = "/board/notice/notice_fail.jsp";
		
		String nextPage = null;
		if(command.equals("noticeSearch.do")) {
			ns.noticeSearch(request);
			nextPage = "/board/notice/notice_list.jsp";
		}
		
		if(command.equals("noticeWriteForm.do")) {
			if(!ManagementService.checkAdmin(request, response)) {
				return;
			}
			nextPage = "/board/notice/notice_write.jsp";
		}
		
		// 게시글 삽입 요청
		if(command.equals("noticeWrite.do")) {
			if(!ManagementService.checkAdmin(request, response)) {
				return;
			}
			boolean isSuccess = ns.noticeWrite(request);
			nextPage = isSuccess ? success : failed;
		}
		
		// 게시글 상세 보기
		if(command.equals("noticeDetail.do")) {
			ns.noticeDetail(request);
			nextPage = "/board/notice/notice_detail.jsp";
		}
		
		// 게시글 페이지 수정 요청
		if(command.equals("noticeUpdateForm.do")) {
			if(!ManagementService.checkAdmin(request, response)) {
				return;
			}
			ns.noticeDetail(request);
			nextPage = "/board/notice/notice_update.jsp";
		}
		
		// 게시글 수정 요청
		if(command.equals("noticeUpdate.do")) {
			if(!ManagementService.checkAdmin(request, response)) {
				return;
			}
			boolean isSuccess = ns.noticeUpdate(request);
			nextPage = isSuccess ? success : failed;
		}
		
		// 게시글 삭제 요청
		if(command.equals("noticeDelete.do")) {
			if(!ManagementService.checkAdmin(request, response)) {
				return;
			}
			nextPage = ns.noticeDelete(request) ? success : failed;
		}
		
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}else {
			request.getRequestDispatcher(failed).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
