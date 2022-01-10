package dao;

import vo.MemberVO;

public interface MemberDAO {

	// 회원가입
	boolean memberJoin(MemberVO member);
	
	// 로그인 처리
	MemberVO memberLogin(String id, String pass);
	
	// id 값으로 사용자 정보 확인 - cookie 자동 로그인
	MemberVO getMemberById(String id);
	
}












