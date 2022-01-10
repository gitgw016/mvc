package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	}
}













