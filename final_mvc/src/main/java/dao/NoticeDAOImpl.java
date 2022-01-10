package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Criteria;
import util.DBCPUtil;
import util.SearchCriteria;
import vo.NoticeVO;

public class NoticeDAOImpl implements NoticeDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public boolean noticeWrite(NoticeVO notice) {
		boolean isSuccess = false;
		String sql = "INSERT INTO notice_board VALUES(null,?,?,?,?,now())";
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNotice_category());
			pstmt.setString(2, notice.getNotice_author());
			pstmt.setString(3, notice.getNotice_title());
			pstmt.setString(4, notice.getNotice_content());
			int result = pstmt.executeUpdate();
			if(result > 0) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public NoticeVO getNoticeVO(int notice_num) {
		NoticeVO vo = null;
		String sql = "SELECT * FROM notice_board WHERE notice_num = ?";
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new NoticeVO(rs.getInt("notice_num"),rs.getString("notice_category"),rs.getString("notice_author"),rs.getString("notice_title"),rs.getString("notice_content"),rs.getTimestamp("notice_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return vo;
	}

	@Override
	public boolean noticeUpdate(NoticeVO notice) {
		boolean isSuccess = false;
		String sql = "UPDATE notice_board SET notice_category = ? , notice_author = ? , notice_title = ? , notice_content = ? WHERE notice_num = ? ";
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getNotice_category());
			pstmt.setString(2, notice.getNotice_author());
			pstmt.setString(3, notice.getNotice_title());
			pstmt.setString(4, notice.getNotice_content());
			pstmt.setInt(5, notice.getNotice_num());
			int result = pstmt.executeUpdate();
			if(result > 0) isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public boolean noticeDelete(int notice_num) {
		boolean isSuccess = false;
		String sql = "DELETE FROM notice_board WHERE notice_num = ?";
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice_num);
			if(pstmt.executeUpdate() > 0) {
				isSuccess = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(pstmt,conn);
		}
		return isSuccess;
	}

	@Override
	public ArrayList<NoticeVO> getNoticeList(Criteria cri) {
		ArrayList<NoticeVO> noticeList = new ArrayList<>();
		SearchCriteria scri = null;
		if(cri instanceof SearchCriteria) {
			scri = (SearchCriteria)cri;
		}
		String sql = "SELECT * FROM notice_board ";
		try {
		conn = DBCPUtil.getConnection();
		boolean isSearchQuery = true;
		int startRow = scri.getStartRow();
		int perPageNum = scri.getPerPageNum();
		String searchName = scri.getSearchName();
		String searchValue = scri.getSearchValue();
		if(searchName == null || searchName.trim().equals("") || searchName.trim().equals("null")) {
			System.out.println("내용 없으");
			isSearchQuery = false;
		}else {
			if(searchValue == null) {
				searchValue = "";
			}
			if(searchName.equals("author")) {
				sql += " WHERE notice_author LIKE ?";
			}else if(searchName.equals("title")) {
				sql += " WHERE notice_title LIKE ?";
			}
		}
		sql += " ORDER BY notice_num DESC limit ?, ?";
		
			pstmt = conn.prepareStatement(sql);
			if(isSearchQuery) {
				pstmt.setString(1, "%"+searchValue+"%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, perPageNum);
			}else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, perPageNum);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				noticeList.add(new NoticeVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getTimestamp(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return noticeList;
	}

	@Override
	public int getTotalCount(Criteria cri) {
		int totalCount = 0;
		SearchCriteria scri = null;
		if(cri instanceof SearchCriteria) {
			scri = (SearchCriteria)cri;
		}
		String sql = "SELECT count(*) FROM notice_board";
		System.out.println(scri);
		if(scri.getSearchValue() == null)scri.setSearchName("");
		conn = DBCPUtil.getConnection();
		try {
			if(scri.getSearchName() == null || scri.getSearchName().trim().equals("") || scri.getSearchName().trim().equals("null")) {
				System.out.println("totalCount 검색 내용 없음");
				pstmt = conn.prepareStatement(sql);	
			}else {
				if(scri.getSearchName().equals("author")) {
					sql += " WHERE notice_author LIKE CONCAT('%',?,'%')";
				}else if(scri.getSearchName().equals("title")) {
					sql += " WHERE notice_title LIKE CONCAT('%',?,'%')";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, scri.getSearchValue());
			}
			System.out.println("search sql : "+sql);
			rs = pstmt.executeQuery();
			if(rs.next()) totalCount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return totalCount;
	}

}
