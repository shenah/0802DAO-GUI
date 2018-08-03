package DAO;

import java.util.List;

public interface MemberDao {
	//데이터 삽입하는 메소드 
	public boolean insertMember (Member member);
	//데이터 수정하는 메소드 
	public boolean updateMember (Member member);
	//데이터 삭제하는 메소드 
	public boolean deleteMember (String id, String pw);
	
	//데이터 전체를 읽어오는 메소드
	//Map이나 DTO의 List로 리턴 
	public List<Member> allMember();
	
	//name을 포함한 닉넴 가지고 조회하는 메소드 
	public List<Member> nameMember(String name);
	
	//회원관리에서 아이디로 회원정보조회 
	public List<Member> idMember(String id);
}
