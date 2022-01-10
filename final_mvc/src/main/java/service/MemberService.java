package service;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import vo.MemberVO;

public interface MemberService {

	// 회원 가입 처리
		void memberJoin(HttpServletRequest request, HttpServletResponse response) throws IOException;
		
		// 로그인 처리
		//  @return true = 로그인 성공, false 로그인 실패
		boolean memberLogin(HttpServletRequest request, HttpServletResponse response);
		
		// 로그아웃 처리 
		void logOut(HttpServletRequest request, HttpServletResponse response);
		
		// 회원 탈퇴 처리
		void withDraw(HttpServletRequest request, HttpServletResponse response);
		
		// 회원 정보 수정
		void memberUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException;
		
		// 비밀번호 찾기
		// @param request id(email), name(사용자 이름)
		// @param response - 인증 - 메일 전송
		void findPassSubmit(HttpServletRequest request, HttpServletResponse response);
		
		// @param request id(email), code
		// @param response - 인증 - 비밀번호 변경 페이지 이동
		void changePassCode(HttpServletRequest request, HttpServletResponse response);
		
		// @param request - id(email), code, pass
		// @param response - 비밀번호 변경 - login
		void changePass(HttpServletRequest request, HttpServletResponse response);
		
		// loginCheck - cookie 자동 로그인 체크
		// @param request - cookie name
		public static void loginCheck(HttpServletRequest request) {
			HttpSession session = request.getSession();
			Object member = session.getAttribute("member");
			Cookie[] cookies = request.getCookies();
			if(cookies != null && member == null) {
				for(int i=0; i<cookies.length; i++) {
					String id = cookies[i].getName();
					System.out.println("cookie id : "+id);
					if(id.equals("id")) {
						String value = cookies[i].getValue();
						MemberDAO dao = new MemberDAOImpl();
						MemberVO vo = dao.getMemberById(value);
						if(vo != null) {
							session.setAttribute("member", vo);
							System.out.println("cookie 요청 처리");
						}
						break;
					}
				}
			}
		}
}
