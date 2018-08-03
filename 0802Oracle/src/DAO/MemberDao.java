package DAO;

import java.util.List;

public interface MemberDao {
	//������ �����ϴ� �޼ҵ� 
	public boolean insertMember (Member member);
	//������ �����ϴ� �޼ҵ� 
	public boolean updateMember (Member member);
	//������ �����ϴ� �޼ҵ� 
	public boolean deleteMember (String id, String pw);
	
	//������ ��ü�� �о���� �޼ҵ�
	//Map�̳� DTO�� List�� ���� 
	public List<Member> allMember();
	
	//name�� ������ �г� ������ ��ȸ�ϴ� �޼ҵ� 
	public List<Member> nameMember(String name);
	
	//ȸ���������� ���̵�� ȸ��������ȸ 
	public List<Member> idMember(String id);
}
