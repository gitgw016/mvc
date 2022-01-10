package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dao.NoticeDAO;
import dao.NoticeDAOImpl;
import util.Criteria;
import util.PageMaker;
import util.SearchCriteria;
import vo.NoticeVO;

public class NoticeServiceImpl implements NoticeService {
	
	NoticeDAO dao = new NoticeDAOImpl();

	@Override
	public boolean noticeWrite(HttpServletRequest request) {
		String notice_author = request.getParameter("notice_author");
		String notice_category = request.getParameter("notice_category");
		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		NoticeVO vo = new NoticeVO(notice_category,notice_author,replaceScript(notice_title),replaceScript(notice_content));
		return dao.noticeWrite(vo);
	}
	
	public String replaceScript(String text){
		text = text.replace("<script>", "&lt;script&gt;");
		text = text.replace("</script>", "&lt;/script&gt;");
		return text;
	}

	@Override
	public boolean noticeUpdate(HttpServletRequest request) {
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		String notice_author = request.getParameter("notice_author");
		String notice_category = request.getParameter("notice_category");
		String notice_title = request.getParameter("notice_title");
		String notice_content = request.getParameter("notice_content");
		NoticeVO vo = new NoticeVO(notice_category,notice_author,replaceScript(notice_title),replaceScript(notice_content));
		vo.setNotice_num(notice_num);
		return dao.noticeUpdate(vo);
	}

	@Override
	public boolean noticeDelete(HttpServletRequest request) {
		int notice_num = Integer.parseInt(request.getParameter("notice_num"));
		return dao.noticeDelete(notice_num);
	}

	@Override
	public void noticeDetail(HttpServletRequest request) {
		String num = request.getParameter("notice_num");
		int notice_num = Integer.parseInt(num);
		NoticeVO vo = dao.getNoticeVO(notice_num);
		request.setAttribute("notice", vo);
	}

	@Override
	public void noticeSearch(HttpServletRequest request) {
		int page = 1;
		String paramPage = request.getParameter("page");
		if(paramPage != null) {
			page = Integer.parseInt(paramPage);
		}
		
		String searchName = request.getParameter("searchName");
		String searchValue = request.getParameter("searchValue");
		System.out.println("searchName : "+searchName);
		System.out.println("searchValue : "+searchValue);
		
		Criteria cri = new SearchCriteria(page,10,searchName,searchValue);
		int totalCount = dao.getTotalCount(cri);
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(totalCount);
		System.out.println(pm);
		ArrayList<NoticeVO> noticeList = dao.getNoticeList(cri);
		if(searchName != null && searchValue != null && !searchValue.trim().equals("")) {
			for(NoticeVO n : noticeList) {
				n.setNotice_author(replaceKeyWord(n.getNotice_author(), searchValue));
				n.setNotice_title(replaceKeyWord(n.getNotice_title(), searchValue));
			}
		}
		request.setAttribute("list", noticeList);
		request.setAttribute("pm", pm);
	}
	
	public String replaceKeyWord(String text, String keyword) {
		return text.replace(keyword, "<b style='color:red'>"+keyword+"</b>");
	}

}
