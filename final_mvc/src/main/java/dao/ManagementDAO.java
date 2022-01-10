package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.DBCPUtil;
import vo.MemberVO;

public class ManagementDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public int getMemberListCount() {
		int listCount = 0;
		
		conn = DBCPUtil.getConnection();
		String sql = "SELECT count(*) FROM mvc_member";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return listCount;
	}
	
	public ArrayList<MemberVO> getPageMemberList(int page, int count){
		// page 요청 페이지 번호
		// 한 페이지에 출력할 회원의 수
		
		// db 검색 시작 인덱스
		int startRow = (page-1)*count;
		ArrayList<MemberVO> memberList = new ArrayList<>();
		String sql = "SELECT * FROM mvc_member ORDER BY num DESC limit ?, ?";
		
		conn = DBCPUtil.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setNum(rs.getInt("num"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				memberList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCPUtil.close(rs,pstmt,conn);
		}
		return memberList;
	}
}
