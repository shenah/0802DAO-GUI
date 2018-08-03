package DAO;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class MemberMain {

	public static void main(String[] args) {
		new MemberVeiw();
		/*//회원가입 혹은 정보수정
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
		
		//인터페이스나 클래스를 상속한 경우 
		//상위 인터페이스나 클래스 이름으로 변수를 만들고 
		//하위 클래스의 인스턴스를 생성해서 대입.
		MemberDao dao = new MemberDaoImpl();
		
		boolean result = dao.insertMember(member);
		
		//회원탈퇴
		MemberDao dao = new MemberDaoImpl();
		boolean result = dao.deleteMember("linyh", "1234");
		
		if(result == true) {
			System.out.println("회원가입 성공!");
		}else {
			System.out.println("회원회원 실패!");
		}
		
		//회원정보 모두 조회
		MemberDao dao = new MemberDaoImpl();
		List <Member> list = dao.allMember();
		
		for(Member m : list) {
			System.out.println(m);
		}*/

	}

}
