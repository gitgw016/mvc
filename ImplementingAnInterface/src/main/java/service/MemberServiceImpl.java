package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	MemberDAO dao = new MemberDAOImpl();

	@Override
	public void memberJoin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String rePass = request.getParameter("rePass");
		String name = request.getParameter("name");
		MemberVO vo = new MemberVO(id,pass,name);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		if(!pass.equals(rePass)) {
			out.print("alert('비밀번호 불일치');");
			out.print("history.go(-1)");
			out.print("</script>");
			return;
		}
		
		MemberVO member = dao.getMemberById(id);
		if(member != null) {
			out.print("alert('이미 사용중인 아이디다');");
			out.print("history.go(-1);");
			out.print("</script>");
			return;
		}
		
		boolean suc = dao.memberJoin(vo);
		if(suc) {
			out.print("alert('가입 성공');");
			out.print("location.href='login.mc';");
		}else {
			out.print("alert('가입 실패');");
			out.print("history.back();");
		}
		out.println("</script>");
	}

	@Override
	public boolean memberLogin(HttpServletRequest request, HttpServletResponse response) {
		boolean logCan = false;
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			String id = request.getParameter("id");
			String pass = request.getParameter("pass");
			String check = request.getParameter("check");
			MemberVO member = dao.memberLogin(id, pass);
			out.print("<script>");
			if(member != null) {
				logCan = true;
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
				if(check != null) {
					Cookie cookie = new Cookie("id",member.getId());
					cookie.setMaxAge(60*60*8);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
				out.print("alert('로그인 성공');");
				out.print("location.href='main.mc';");
			}else {
				out.print("alert('로그인 실패');");
				out.print("history.back();");
			}
			out.print("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logCan;
	}

	@Override
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=0; i<cookies.length; i++) {
				if(cookies[i].getName().equals("id")) {
					Cookie cookie = new Cookie("id","");
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

}
