package DAO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class MemberMain {

	public static void main(String[] args) {
		new MemberVeiw();
		/*//ȸ������ Ȥ�� ��������
		Member member = new Member();
		
		member.setId("linyh");
		member.setPw("2856");
		member.setEmail("lyh_94784@naver.com");
		member.setNickname("LIN");
		member.setPhone("01075892058");
		
		Calendar cal = Calendar.getInstance();
		cal.set(1989,1, 30);
		Date birthday = new Date(cal.getTimeInMillis());
		member.setBrithday(birthday);
		
		//�������̽��� Ŭ������ ����� ��� 
		//���� �������̽��� Ŭ���� �̸����� ������ ����� 
		//���� Ŭ������ �ν��Ͻ��� �����ؼ� ����.
		MemberDao dao = new MemberDaoImpl();
		
		boolean result = dao.insertMember(member);
		
		//ȸ��Ż��
		MemberDao dao = new MemberDaoImpl();
		boolean result = dao.deleteMember("linyh", "1234");
		
		if(result == true) {
			System.out.println("ȸ������ ����!");
		}else {
			System.out.println("ȸ��ȸ�� ����!");
		}
		
		//ȸ������ ��� ��ȸ
		MemberDao dao = new MemberDaoImpl();
		List <Member> list = dao.allMember();
		
		for(Member m : list) {
			System.out.println(m);
		}*/

	}

}
