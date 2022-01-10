package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 전체 경로
		String requestPath = request.getRequestURI();
		System.out.println("전체 요청 경로 : " + requestPath);	
		String contextPath = request.getContextPath();
		System.out.println("요청 프로젝트 경로 : "+ contextPath);
		String command = requestPath.substring(contextPath.length()+1);
		System.out.println("실제 요청(command) 경로 : "+command);
		
		String nextPage = null;
		if(command.equals("main.do")) {
			nextPage = "/common/main.jsp";
		}else if(command.equals("join.do")) {
			nextPage = "/member/join.jsp";
		}else if(command.equals("login.do")) {
			nextPage = "/member/login.jsp";
		}
		
		System.out.println("nextPage : "+nextPage);
		
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);;
		}else {
			request.getRequestDispatcher("/common/main.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
