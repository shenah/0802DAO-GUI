package DAO;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MemberVeiw extends JFrame {

	// 레이블 선언
	JLabel lblId, lblNickname, lblPw, lblEmail, lblPhone, lblBrithday;
	// 입력받을 텍스트 필드 선언
	JTextField tfId, tfNickname, tfPw, tfEmail, tfPhone, tfBirthday;
	// 버튼
	JButton btnFrist, btnPrev, btnNext, btnLast, btnInsert, btnUpdate, btnDelete, btnSearch, btnClear;
	//
	JLabel lblIndex;
	// 생성자
	MemberDao dao = new MemberDaoImpl();
	//
	List<Member> list;

	public MemberVeiw() {
		centerDisplay();
		southDisplay();
		setBounds(400, 400, 600, 700);
		setTitle("회원관리");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void centerDisplay() {
		JPanel centerPanel = new JPanel();
		lblId = new JLabel("아이디", JLabel.RIGHT);

		tfId = new JTextField();
		lblNickname = new JLabel("닉넴", JLabel.RIGHT);
		tfNickname = new JTextField();
		lblPw = new JLabel("비밀번호", JLabel.RIGHT);
		tfPw = new JTextField();
		lblEmail = new JLabel("이메일", JLabel.RIGHT);
		tfEmail = new JTextField();
		lblPhone = new JLabel("전화번호", JLabel.RIGHT);
		tfPhone = new JTextField();
		lblBrithday = new JLabel("생일", JLabel.RIGHT);
		tfBirthday = new JTextField();

		centerPanel.setLayout(new GridLayout(6, 2, 5, 5));

		centerPanel.add(lblId);
		centerPanel.add(tfId);
		centerPanel.add(lblNickname);
		centerPanel.add(tfNickname);
		centerPanel.add(lblPw);
		centerPanel.add(tfPw);
		centerPanel.add(lblEmail);
		centerPanel.add(tfEmail);
		centerPanel.add(lblPhone);
		centerPanel.add(tfPhone);
		centerPanel.add(lblBrithday);
		centerPanel.add(tfBirthday);

		add("Center", centerPanel);
	}

	private void southDisplay() {
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 5, 7, 7));
		panel2.setBorder(new TitledBorder("조회"));
		btnFrist = new JButton("처음으로");
		btnPrev = new JButton("이전");
		btnNext = new JButton("다음");
		btnLast = new JButton("마지막으로");

		lblIndex = new JLabel("인덱스", JLabel.CENTER);

		panel2.add(btnFrist);
		panel2.add(btnPrev);
		panel2.add(lblIndex);
		panel2.add(btnNext);
		panel2.add(btnLast);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 5, 7, 7));
		panel3.setBorder(new TitledBorder("작업"));
		
		btnInsert = new JButton("삽입");
		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//lblIndex의 텍스트 가 삽이이 아니면 메세지 박스를 출력하고 함수의 수행을 종료
				//문자열의 동일성 여부는 == 아니고 equals
				if(lblIndex.getText().equals("삽입") == false) {
					JOptionPane.showMessageDialog(null, "지우고 쓰세요.","삽입에러", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//입력한 데이터를 가져오기 
				String id = tfId.getText();
				String nickname = tfNickname.getText();
				String pw = tfPw.getText();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				String birthday = tfBirthday.getText();
				
				//입력한 데이터 유효성검사.
				//1. 입력한 아이디가 필수 입력이고 10자리이상 인지 검사
				if(id.trim().length()>10) {
					JOptionPane.showMessageDialog(null, "10자이내로 입력하세요.", "입력오류", JOptionPane.ERROR_MESSAGE);
				}
				
				//2. 입력한 전화번호는 필수 입력, 전부숫자, 11자리
				if(phone.trim().length() != 11) {
					JOptionPane.showMessageDialog(null, "11자로 입력하세요.", "입력오류", JOptionPane.ERROR_MESSAGE);
				}
				int i = 0;
				while(i < phone.length()) {
					char ch = phone.charAt(i);
					if(ch<'0' || ch> '9') break;
					i = i + 1;
				}
				//birthday yyyy-mm-dd 형식으로 입력되었다고 가정할때 자료형 변경.
				int year = Integer.parseInt(birthday.substring(0, 4));
				int month = Integer.parseInt(birthday.substring(5, 7));
				int day = Integer.parseInt(birthday.substring(8,10));
				Calendar cal = Calendar.getInstance();
				cal.set(year, month-1, day);
				Date date = new Date(cal.getTimeInMillis());
				
				Member member = new Member();
				member.setId(id);
				member.setNickname(nickname);
				member.setPw(pw);
				member.setEmail(email);
				member.setPhone(phone);
				member.setBirthday(date);
				
				dao.insertMember(member);
				list = dao.allMember();
				int idx = list.size() -1;
				
			}
			
		});
		
		btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 입력한 텍스트 전부 가져오기
				String id = tfId.getText();
				String Nickname = tfNickname.getText();
				String pw = tfPw.getText();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				String birthday = tfBirthday.getText();

				// ID의 데이터가 비어있으면 메세지 박스를 출력하고 리턴하고 출력하기
				if (id.trim().length() < 1) {
					JOptionPane.showMessageDialog(null, "아이디는 필수입니다.", "입력오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// password의 데이터가 비어있으면 메세지 박스 출력하고 리턴하고 출력하기
				if (pw.trim().length() < 1) {
					JOptionPane.showMessageDialog(null, "비밀번호는 필수입니다.", "입력오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 날짜 데이터가 년도4자리-월2자리-일2자리 형식인지 조사
				int i = 0;
				while (i < birthday.length()) {
					char ch = birthday.charAt(i);
					if (i >= 0 || i <= 3) {
						if (ch < '0' || ch > '9')
							break;
					}
					if (i == 5 || i == 6) {
						if (ch < '0' || ch > '9')
							break;
					}
					if (i == 8 || i == 9) {
						if (ch < '0' || ch > '9')
							break;
					}

					i = i + 1;

				}
				if (i != birthday.length()) {
					JOptionPane.showMessageDialog(null, "생년월일은 YYYY-MM-DD 형식으로 입력해주세요.");
					return;
				}
			}

		});
		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
				System.out.println(r);
				if (r == 0) {
					JOptionPane.showMessageDialog(null, "삭제성공", "삭제", JOptionPane.INFORMATION_MESSAGE);
				}
				dao.deleteMember(tfId.getText(), tfPw.getText());
			}

		});

		btnSearch = new JButton("조회");
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				list = dao.idMember(tfId.getText());
				for (Member m : list) {
					tfNickname.setText(m.getNickname());
					tfPw.setText(m.getPw());
					tfEmail.setText(m.getEmail());
					tfPhone.setText(m.getPhone());
					tfBirthday.setText(String.format("%s", m.getBirthday()));
				}
				JOptionPane.showMessageDialog(null, "해당 회원이 없습니다. 아이디를 확인해보세요.");
			}

		});

		btnClear = new JButton("지움");
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 텍스 필드들의 텍스트를 전부 삭제
				tfId.setText("");
				tfNickname.setText("");
				tfPw.setText("");
				tfEmail.setText("");
				tfPhone.setText("");
				tfBirthday.setText("");
				
				//lblIndex의 타이틀을 삽입으로 변경
				lblIndex.setText("삽입");
			}
		});

		panel3.add(btnInsert);
		panel3.add(btnUpdate);
		panel3.add(btnDelete);
		panel3.add(btnSearch);
		panel3.add(btnClear);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		southPanel.add("Center", panel2);
		southPanel.add("South", panel3);

		add("South", southPanel);
	}
}
