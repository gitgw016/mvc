package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ManagementService;
import vo.MemberVO;

@WebServlet("*.mm")
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ManagementService service = new ManagementService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getRequestURI().substring(request.getContextPath().length()+1);
		System.out.println("Management command : "+command);
		
		String nextPage = null;
		
		if(command.equals("managementPage.mm")) {
			boolean isCheck = ManagementService.checkAdmin(request, response);
			if(!isCheck) {
				return;
			}
			// 관리자
			ArrayList<MemberVO> memberList = service.getMemberList(request);
			request.setAttribute("memberList", memberList);
			nextPage = "/member/management.jsp";
		}
		if(nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
