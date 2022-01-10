package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.Criteria;
import util.DBCPUtil;
import vo.CommentVO;

public class CommentDAOImpl implements CommentDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	@Override
	public boolean insertComment(CommentVO vo) {
		
		conn = DBCPUtil.getConnection();
		String sql = "INSERT INTO qna_comment "
				+ " VALUES(null,?,?,?,now(),'N',?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getComment_id());
			pstmt.setString(2, vo.getComment_name());
			pstmt.setString(3, vo.getComment_content());
			pstmt.setInt(4, vo.getComment_board_num());
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(pstmt,conn);
		}
		return false;
	}

	@Override
	public int getCommentTotalCount(int qna_num) {
		int totalCount = 0;
		String sql = "SELECT count(*) FROM qna_comment "
					+" WHERE comment_board_num = ?";
		try {
			conn = DBCPUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBCPUtil.close(rs,pstmt,conn);
		}
		return totalCount;
	}

	@Override
	public ArrayList<CommentVO> getCommentList(int qna_num, Criteria cri) {
		ArrayList<CommentVO> list = new ArrayList<>();
		String sql = "SELECT * FROM qna_comment "
				+ " WHERE comment_board_num = ? "
				+ " ORDER BY comment_num DESC limit ?, ?";
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.setInt(2, cri.getStartRow());
			pstmt.setInt(3, cri.getPerPageNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentVO cv = new CommentVO(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getTimestamp(5),
					rs.getString(6),
					rs.getInt(7)
				);
				list.add(cv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return list;
	}

	@Override
	public boolean deleteComment(int comment_num, String id) {
		conn = DBCPUtil.getConnection();
		String sql = "UPDATE qna_comment SET "
				+ " comment_delete = 'Y' "
				+ " WHERE comment_num = ? "
				+ " AND comment_id = ? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			pstmt.setString(2, id);
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {DBCPUtil.close(pstmt,conn);}
		return false;
	}
}








