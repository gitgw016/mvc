package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManagementDAO;
import vo.MemberVO;
import vo.PageInfo;

public class ManagementService {
	
	ManagementDAO dao = new ManagementDAO();
	// 페이징 처리된 회원 목록
	public ArrayList<MemberVO> getMemberList(HttpServletRequest request){
		int defaultPage = 1;	// 현재 사용자가 보고자 하는 페이지 번호
		int pageCount = 10;		// 한번에 보여줄 회원 목록 수
		int displayPageNum = 7;	// 한번에 보여줄 블럭 개수
		String page = request.getParameter("page");
		if(page != null) {
			defaultPage = Integer.parseInt(page);
		}
		// 전체 회원 목록 수
		int listCount = dao.getMemberListCount();
		System.out.println("전체 회원 수 : "+listCount);
		
		int startPage = (defaultPage-1)/displayPageNum * displayPageNum + 1;
		int endPage = startPage+displayPageNum - 1;
		System.out.println("endPage : "+endPage);
		System.out.println("startPage : "+startPage);
		int maxPage = ((listCount-1)/pageCount)+1;
		System.out.println("maxPage : "+maxPage);
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		System.out.println("수정된 endPage : "+endPage);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setPage(defaultPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setListCount(listCount);
		System.out.println(pageInfo);
		request.setAttribute("pageInfo", pageInfo);
		return dao.getPageMemberList(defaultPage, pageCount);
	}
	
	// 관리자 확인
	public static boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) {
		boolean isCheck = false;
		MemberVO member = (MemberVO)request.getSession().getAttribute("member");
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(member == null) {
				out.print("<script>alert('로그인 먼저 ㅉㅉ');");
				out.print("location.href='login.mc';</script>");
				return isCheck;
			}
			
			if(!member.getId().equals("admin")) {
				out.print("<script>alert('넌 못해 임마! 나가!');");
				out.print("history.back();</script>");
				return isCheck;
			}
			isCheck = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isCheck;
	}
}
