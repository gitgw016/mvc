package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.CommentDAO;
import dao.CommentDAOImpl;
import util.Criteria;
import util.PageMaker;
import vo.CommentVO;

public class CommentServiceImpl implements CommentService {

	CommentDAO dao = new CommentDAOImpl();
	
	@Override
	public boolean insertComment(HttpServletRequest request) {
		String comment_id = request.getParameter("id");
		String comment_name = request.getParameter("name");
		String comment_content = request.getParameter("comment_content");
		String qna_num = request.getParameter("qna_num");
		int comment_board_num = Integer.parseInt(qna_num);
		
		CommentVO cv = new CommentVO(
			comment_id,
			comment_name,
			comment_content,
			comment_board_num
		);
		return dao.insertComment(cv);
	}

	@Override
	public Map<String, Object> getCommentList(HttpServletRequest request) {
		Map<String, Object> hm = new HashMap<>();
		int qna_num
		= Integer.parseInt(request.getParameter("qna_num"));
		String paramPage = request.getParameter("page");
		int page = 1;
		if(paramPage != null) {
			page = Integer.parseInt(paramPage);
		}
		
		int totalCount = dao.getCommentTotalCount(qna_num);
		Criteria cri = new Criteria(page,5);
		ArrayList<CommentVO> list = dao.getCommentList(qna_num, cri);
		hm.put("list", list);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(totalCount);
		hm.put("pm", pm);
		return hm;
	}

	@Override
	public boolean deleteComment(HttpServletRequest request)
	{
		int comment_num
		= Integer.parseInt(request.getParameter("comment_num"));
		String id = request.getParameter("id");
		return dao.deleteComment(comment_num, id);
	}

}






