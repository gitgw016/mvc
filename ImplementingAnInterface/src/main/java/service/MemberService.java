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
	void memberJoin(HttpServletRequest requst,
			HttpServletResponse response) throws IOException;
	
	// 로그인 처리
	/**
	 * @return true = 로그인성공 , false 로그인실패
	 */
	boolean memberLogin(HttpServletRequest requst,
			HttpServletResponse response);
	
	// 로그아웃 처리
	void logOut(HttpServletRequest requst,
			HttpServletResponse response);
	
	public static void loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Object member = session.getAttribute("member");
		Cookie[] cookies = request.getCookies();
		if(cookies != null && member == null) {
			for(int i=0; i<cookies.length; i++) {
				String id = cookies[i].getName();
				if(id.equals("id")) {
					String value = cookies[i].getValue();
					MemberDAO dao = new MemberDAOImpl();
					MemberVO vo = dao.getMemberById(value);
					if(vo != null) {
						session.setAttribute("member", vo);
					}
					break;
				}
			}
		}
	}
}













