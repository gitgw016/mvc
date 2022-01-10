package dao;

import vo.MemberVO;

// mvc_member table 요청 처리
// Database Access Object
public interface MemberDAO {
	// 회원가입
	boolean memberJoin(MemberVO member);
	
	// 로그인 처리
	MemberVO memberLogin(String id, String pass);
	
	// 회원정보 수정
	boolean memberUpdate(MemberVO member);
	
	// id 값으로 사용자 정보 확인 - cookie 자동 로그인
	MemberVO getMemberById(String id);
	
	// 회원 탈퇴 처리 = joinYN - 'N'
	void withDrawMember(int num);
	
	// 비밀번호 찾기
	// 사용자 정보 확인
	boolean checkMember(String id, String name);
	
	// 코드 등록
	void addPassCode(String id, String code);
	
	// 코드 확인
	public boolean checkPassCode(String id, String code);
	
	// 비밀번호 변경
	public void changePass(String id, String pass);
	
}
