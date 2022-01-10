package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("*.mc")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService ms = new MemberServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("memberController GET 요청");
		
		MemberService.loginCheck(request);
		
		String command = getCommand(request);
		
		String nextPage = null;
		
		if(command.equals("login.mc")) {
			nextPage = "/member/login.jsp";
		}
		
		if(command.equals("join.mc")) {
			nextPage = "/member/join.jsp";
		}
		
		if(command.equals("info.mc")) {
			nextPage = "/member/info.jsp";
			// 회원 정보 페이지
		}
		
		if(command.equals("logOut.mc")) {
			// 로그 아웃 처리
			ms.logOut(request, response);
			nextPage = "/common/main.jsp";	
		}
		
		if(command.equals("update.mc")) {
			nextPage = "/member/update.jsp";
			// 회원정보 수정 페이지 요청
		}
		
		if(command.equals("withdraw.mc")) {
			nextPage = "/member/withdraw.jsp";
			// 회원 탈퇴 페이지 요청
		}
		
		if(command.equals("findPass.mc")) {
			// 비밀번호 찾기 화면 요청
			nextPage = "/member/findPass.jsp";
		}
		
		if(nextPage != null) {
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("memberController POST 요청");
		
		MemberService.loginCheck(request);
		
		request.setCharacterEncoding("utf-8");
		String command = getCommand(request);
		// String nextPage = "";
		
		if(command.equals("loginSubmit.mc")) {
			// 로그인 요청 처리
			if(ms.memberLogin(request, response)) {
				response.sendRedirect(request.getContextPath()+"/test");
			}else {
				response.sendRedirect(request.getContextPath()+"/login.mc");
			}
		}
		
		if(command.equals("joinSubmit.mc")){
			// 회원 가입 요청 처리
			ms.memberJoin(request, response);
			return;
		}
		
		if(command.equals("updateSubmit.mc")) {
			// 회원 정보 수정 요청 처리
			ms.memberUpdate(request, response);
			return;
		}
		
		if(command.equals("withDrawSubmit.mc")) {
			// 회원 탈퇴 요청 처리
			ms.withDraw(request, response);
			return;
		}
		
		if(command.equals("findPassSubmit.mc")) {
			// 비밀번호 찾기 결과 요청 처리 - 메일 전송
			ms.findPassSubmit(request, response);
			return;
		}
		
		if(command.equals("passAccept.mc")) {
			// 코드 확인 요청
			ms.changePassCode(request, response);
			return;
		}
		
		if(command.equals("changePass.mc")) {
			// 비밀번호 변경 요청
			ms.changePass(request, response);
			return;
		}
		
		/*
		 * if(nextPage != null) {
		 * request.getRequestDispatcher(nextPage).forward(request, response); }
		 */
	}
	// request 정보를 넘겨받아 요청 URL(command)를 반환
	private String getCommand(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestPath.substring(contextPath.length()+1);
		System.out.println("MemberController 요청 : " + command);
		return command;
	}
}
