package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.CommentService;
import service.CommentServiceImpl;

@WebServlet("*.co")
public class AJAXCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CommentService cs = new CommentServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String cmd = request.getRequestURI().substring(request.getContextPath().length()+1);
		
		Gson gson = new Gson();
		String json = null;
		
		if(cmd.equals("commentWrite.co")) {
			// 댓글 작성 요청
			boolean isSuccess = cs.insertComment(request);
			json = gson.toJson(isSuccess);
		}else if(cmd.equals("list.co")) {
			// 댓글 리스트 요청
			Map<String, Object> hm = cs.getCommentList(request);
			json = gson.toJson(hm);
			// {hm:[list : [{댓글목록}], pm : {pageMaker}]};
			System.out.println(json);
		}else if(cmd.equals("commentDelete.co")) {
			// 댓글 삭제 요청
			boolean isSuccess = cs.deleteComment(request);
			json = gson.toJson(isSuccess);
		}
		
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
