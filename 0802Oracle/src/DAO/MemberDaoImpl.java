package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
	//�Բ� ���� ����
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//�����ͺ��̽� �������ִ�  �޼ҵ�
	private void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	//�����ͺ��̽� ���� �������ִ� �޼ҵ� 
	private void disconnect(){
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {}
		}
	// �����ͺ��̽����� ���� ū num ã�� �о���� �޼ҵ� - �ڵ���ȣ����
	private int getMaxNum() {
		connect();
		int result = 0;
		try {
			pstmt = con.prepareStatement("select max(num) from member");
			
			// sql ���� - true / false ����
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
	
	//������ ���� - ȸ�� ����
	@Override
	public boolean insertMember(Member member) {
		boolean result = false;
		int maxNum = getMaxNum(); //getMaxNum()���� connect�� �����ϱ⿡ �� �޼ҵ忡���� connect()���� ��� ���� �Ѵ�. 
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

			// sql ���� executeUpdate�� ����
			// ���� ���� ������� ���� ����
			int r = pstmt.executeUpdate();
			if (r == 1) result = true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
		disconnect();
		return result;
	}

	//������ ���� - ȸ������ ����
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

	//������ ���� - ȸ��Ż��
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

	//��� ������ ��ȸ
	@Override
	public List<Member> allMember() {
		//�о�� �����͸� list�� ����
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

	//�гۿ� name�� ���Ե� ������ �˻�
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
	
	//ȸ���������� id�� ȸ������ ��ȸ 
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
