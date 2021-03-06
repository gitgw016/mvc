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
		System.out.println("get");
		MemberService.loginCheck(request);
		
		String cmd = getCommand(request);
		
		String next = null;
		
		if(cmd.equals("login.mc") || cmd.equals("common/login.mc")) {
			next = "/member/login.jsp";
		}
		
		if(cmd.equals("add.mc") || cmd.equals("common/add.mc")) {
			next = "/member/join.jsp";
		}
		
		if(cmd.equals("main.mc") || cmd.equals("common/main.mc")) {
			next = "/common/main.jsp";
		}
		
		if(cmd.equals("logOut.mc") || cmd.equals("common/logOut.mc")) {
			ms.logOut(request, response);
			next = "/common/main.jsp";
		}
		
		if(next != null) {
			RequestDispatcher re = request.getRequestDispatcher(next);
			re.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		MemberService.loginCheck(request);
		
		request.setCharacterEncoding("utf-8");
		String cmd = getCommand(request);
		
		if(cmd.equals("join.mc") || cmd.equals("common/join.mc")) {
			ms.memberJoin(request, response);
			return;
		}
		
		if(cmd.equals("logOn.mc") || cmd.equals("common/logOn.mc")) {
			ms.memberLogin(request, response);
			return;
		}
	}

	private String getCommand(HttpServletRequest request) {
		String requestPath = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestPath.substring(contextPath.length()+1);
		System.out.println(command);
		return command;
	}
}

