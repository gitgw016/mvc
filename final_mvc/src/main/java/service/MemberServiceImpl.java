package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import util.GoogleAuthenticator;
import vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	MemberDAO dao = new MemberDAOImpl();

	@Override
	public void memberJoin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String rePass = request.getParameter("rePass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		MemberVO vo = new MemberVO(id,pass,name,age,gender);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print("<script>");
		if(!pass.equals(rePass)) {
			pw.print("alert('비밀번호 불일치.');");
			pw.print("history.go(-1);");
			pw.print("</script>");
			return;
		}
		// id로 사용자 정보 찾기
		MemberVO member = dao.getMemberById(id);
		if(member != null) {
			pw.print("alert('사용중인 아이디.');");
			pw.print("history.go(-1);");
			pw.print("</script>");
			return;
		}
		
		boolean isSuccess = dao.memberJoin(vo);
		if(isSuccess) {
			pw.print("alert('가입 성공');");
			pw.print("location.href='login.mc';");
		}else {
			pw.print("alert('가입 실패');");
			pw.print("history.back();");
		}
		pw.println("</script>");
	}

	@Override
	public boolean memberLogin(HttpServletRequest request, HttpServletResponse response) {
		boolean isLogin = false;
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String check = request.getParameter("check");
		
		MemberVO member = dao.memberLogin(id, pass);
		if(member != null) {
			isLogin = true;
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			if(check != null) {
				Cookie cookie = new Cookie("id",member.getId());
				cookie.setMaxAge(60*60*24*15);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		return isLogin;
	}

	@Override
	public void logOut(HttpServletRequest request, HttpServletResponse response) {
		// session 삭제 & cookie 정보 삭제
		HttpSession session = request.getSession();
		// 새로운 세션 객체 생성
		//session.invalidate();
		// 단일 속성 삭제
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

	@Override
	public void withDraw(HttpServletRequest request, HttpServletResponse response) {
		String tempPass = request.getParameter("tempPass");
		System.out.println("tempPass : "+tempPass);
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO)session.getAttribute("member");
		String id = member.getId();
		// id와 임시작성된 비밀번호가 일치하는 사용자 확인
		member = dao.memberLogin(id, tempPass);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			if(member != null) {
				// 회원정보 일치 -> 삭제
				dao.withDrawMember(member.getNum());
				// 탈퇴한 회원의 session, cookie 정보 삭제
				logOut(request,response);
				out.print("alert('탈퇴 완료 ㅠㅠ');");
				out.print("location.href='test';");
			}else {
				// 정보 불일치
				out.print("alert('탈퇴 실패 ^^');");
				out.print("history.go(-1);");
			}
			out.print("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void memberUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberVO member = new MemberVO();
		member.setNum(Integer.parseInt(request.getParameter("num")));
		member.setPass(request.getParameter("pass"));
		member.setName(request.getParameter("name"));
		member.setGender(request.getParameter("gender"));
		member.setAge(Integer.parseInt(request.getParameter("age")));
		System.out.println("update member : "+member);
		
		boolean isUpdate = dao.memberUpdate(member);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String url = "update.mc";
		String msg = "수정 실패";
		out.print("<script>");
		if(isUpdate) {
			String id = request.getParameter("id");
			MemberVO updateMember = dao.getMemberById(id);
			request.getSession().setAttribute("member", updateMember);
			url = "info.mc";
			msg = "수정 완료";
		}
		out.print("alert('"+msg+"');");
		out.print("location.href=('"+url+"');");
		out.print("</script>");
	}

	@Override
	public void findPassSubmit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		boolean isCheck = dao.checkMember(id, name);
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			if(!isCheck) {
				out.print("<script>");
				out.print("alert('전송 못함 ㅅㄱ');");
				out.print("history.go(-1);");
				out.print("</script>");
				return;
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<5; i++) {
				// 0 ~ 9
				int random = (int)(Math.random()*10);
				sb.append(random);
			}
			String code = sb.toString();
			System.out.println("code : "+code);
			
			GoogleAuthenticator auth = new GoogleAuthenticator();
			Session session = Session.getDefaultInstance(auth.getProperties(),auth);
			InternetAddress toAddress = new InternetAddress(id);
			InternetAddress fromAddress = new InternetAddress("god@heaven.div","ㅎ");
			MimeMessage msg = new MimeMessage(session);
			// 메일 전송 시간
			msg.setSentDate(new Date());
			msg.setHeader("Content-Type", "text/html;charset=utf-8");
			msg.setRecipient(Message.RecipientType.TO, toAddress);
			msg.setFrom(fromAddress);
			msg.setSubject("비밀번호 코드발송","utf-8");
			
			String ipAddress = request.getRemoteHost();
			if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
				InetAddress inetAddress = InetAddress.getLocalHost();
				ipAddress = inetAddress.getHostAddress();
			}
			ipAddress += ":8080";
			System.out.println("ipAddress : "+ipAddress);
			StringBuilder mail = new StringBuilder();
			mail.append("<!DOCTYPE html>");
			mail.append("<html>");
			mail.append("<head>");
			mail.append("<meta charset='utf-8'/>");
			mail.append("<title>비밀번호 찾기</title>");
			mail.append("</head>");
			mail.append("<body>");
			mail.append("<h1>비 밀 번 호</h1>");
			mail.append("<form action='http://"+ipAddress+request.getContextPath()+"/passAccept.mc' method='post' ");
			mail.append("onsubmit='window.open(\'\',\'w\')' target='w' >");
			mail.append("<input type='hidden' name='id' value='"+id+"'/> ");
			mail.append("<input type='hidden' name='code' value='"+code+"'/> ");
			mail.append("<input type='submit' value='이메일 인증 완료'/> ");
			mail.append("</form>");
			mail.append("</body>");
			mail.append("</html>");
			
			String content = mail.toString();
			System.out.println("mail content : "+content);
			msg.setContent(content,"text/html;charset=utf-8");
			Transport.send(msg);
			dao.addPassCode(id, code);
			System.out.println("코드 등록함 : "+id+" , "+code);
			out.print("<script>alert('전송 성공했으니 확인하러 ㄱㄱ');");
			out.print("location.href='test';");
			out.print("</script>");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("<script>alert('발송 실패함 ㅅㄱ');");
			out.print("location.href='findPass.mc';");
			out.print("</script>");
		}
	}

	@Override
	public void changePassCode(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		System.out.println("id : "+id+" , code : "+code);
		
		boolean isCheck = dao.checkPassCode(id, code);
		
		response.setContentType("text/html;charset=utf-8");
		try {
			if(isCheck) {
				// 일치
				request.setAttribute("id", id);
				request.setAttribute("code", code);
				request.getRequestDispatcher("/member/changePass.jsp").forward(request, response);
			}else {
				// 불일치
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('잘못됐다');");
				out.print("location.href='login.mc';");
				out.print("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changePass(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String pass = request.getParameter("pass");
		
		boolean isCheck = dao.checkPassCode(id, code);
		
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			if(isCheck) {
				// 비밀번호 변경
				dao.changePass(id, pass);
				out.print("alert('변경했다 ㅇㅇ');");
			}else {
				// 정보 재확인
				out.print("alert('처리못함 ㅎㅎ');");
			}
			out.print("location.href='login.mc';");
			out.print("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
