package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
	//함께 쓰는 변수
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//데이터베이스 연결해주는  메소드
	private void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//데이터베이스 연결 해제해주는 메소드 
	private void disconnect(){
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
	// 데이터베이스에서 가장 큰 num 찾아 읽어오는 메소드 - 자동번호생성
	private int getMaxNum() {
		connect();
		int result = 0;
		try {
			pstmt = con.prepareStatement("select max(num) from member");
			
			// sql 실행 - true / false 리턴
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		disconnect();
		return result;
	}
	
	//데이터 삽입 - 회원 가입
	@Override
	public boolean insertMember(Member member) {
		boolean result = false;
		int maxNum = getMaxNum(); //getMaxNum()에서 connect를 수행하기에 이 메소드에서는 connect()전에 결과 얻어야 한다. 
		try {
			connect();
			pstmt = con.prepareStatement("insert into member(id, num, password, email, phone, nickname, brithday) "
					+ "values(?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, member.getId());
			pstmt.setInt(2, maxNum + 1);
			pstmt.setString(3, member.getPw());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getNickname());
			pstmt.setDate(7, member.getBirthday());

			// sql 실행 executeUpdate로 실행
			// 리턴 값은 영향받은 행의 개수
			int r = pstmt.executeUpdate();
			if (r == 1) result = true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		disconnect();
		return result;
	}

	//데이터 변경 - 회원정보 수정
	@Override
	public boolean updateMember(Member member) {
		boolean result = false;
		connect();
		try {
			pstmt = con.prepareStatement("update member set password=?, email=?, phone=?, nickname=?, brithday=?  where id = ?");
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getNickname());
			pstmt.setDate(5, member.getBirthday());
			pstmt.setString(6, member.getId());
			
			int r = pstmt.executeUpdate();
			if(r == 1) result = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		disconnect();
		return result;
	}

	//데이터 삭제 - 회원탈퇴
	@Override
	public boolean deleteMember(String id, String pw) {
		boolean result = false;
		connect();
		try {
			pstmt = con.prepareStatement("delete from member where id = ? and password = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			int r = pstmt.executeUpdate();
			if(r == 1) result = true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		disconnect();
		return result;
	}

	//모든 데이터 조회
	@Override
	public List<Member> allMember() {
		//읽어온 데이터를 list에 저장
		List <Member> list = new ArrayList<>();
		connect();
		try {
			pstmt = con.prepareStatement("select id, num, password, email, phone, nickname, brithday from member order by num");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString(1));
				member.setNum(rs.getInt(2));
				member.setPw(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setPhone(rs.getString(5));
				member.setNickname(rs.getString(6));
				member.setBirthday(rs.getDate(7));
				list.add(member);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		disconnect();
		return list;
	}

	//닉넴에 name이 포함된 데이터 검색
	@Override
	public List<Member> nameMember(String name) {
		List <Member> list = new ArrayList<>();
		connect();
		try {
			pstmt = con.prepareStatement("select * from member where nickname like '%?%'");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString(1));
				member.setNum(rs.getInt(2));
				member.setPw(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setPhone(rs.getString(5));
				member.setNickname(rs.getString(6));
				member.setBirthday(rs.getDate(7));
				list.add(member);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		disconnect();
		return list;
	}
	
	//회원관리에서 id로 회원정보 조회 
	@Override
	public List<Member> idMember(String id) {
		List <Member> list = new ArrayList<>();
		connect();
		try {
			pstmt = con.prepareStatement("select * from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setId(rs.getString(1));
				member.setNum(rs.getInt(2));
				member.setPw(rs.getString(3));
				member.setEmail(rs.getString(4));
				member.setPhone(rs.getString(5));
				member.setNickname(rs.getString(6));
				member.setBirthday(rs.getDate(7));
				list.add(member);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		disconnect();
		return list;
	}

}
