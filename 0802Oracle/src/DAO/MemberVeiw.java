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
	// �ʿ��� UI���� ����

	// ���̺� ����
	JLabel lblId, lblNickname, lblPw, lblEmail, lblPhone, lblBrithday;
	// �Է¹��� �ؽ�Ʈ �ʵ� ����
	JTextField tfId, tfNickname, tfPw, tfEmail, tfPhone, tfBirthday;
	// ��ư ����
	JButton btnFrist, btnPrev, btnNext, btnLast, btnInsert, btnUpdate, btnDelete, btnSearch, btnClear;
	// ���� ��� ���� �������� �ε����� ǥ���� ���̺�
	JLabel lblIndex;
	// �����ͺ��̽� �۾��� ���� DAO ����
	MemberDao dao = new MemberDaoImpl();
	// �����ͺ��̽����� ������ �����͸� �����ϱ� ���� list
	List<Member> list;

	// ���� ��� ���� �������� �ε����� ������ ����
	int idx;

	// idx��° �����͸� ȭ�鿡 ������ִ� �޼ҵ�
	// �� ó�� �ѹ� ȣ���ϰ� �����Ϳ� �۾��� �߻��ϸ� ȣ���ϴ� �޼ҵ�
	private void printData() {
		// ���� �����Ͱ� ���ٸ� �޼��� �ڽ��� ����ϰ� return
		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "���� �����Ͱ� �����ϴ�.", "������ ����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		// list�� idx��° �����͸� �����ɴϴ�.
		Member m = list.get(idx);
		tfId.setText(m.getId());
		tfNickname.setText(m.getNickname());
		tfPw.setText(m.getPw());
		tfEmail.setText(m.getEmail());
		tfPhone.setText(m.getPhone());
		// ������ date Ÿ������ toString�� ȣ���ؼ� String���� ��ȯ
		tfBirthday.setText(m.getBirthday().toString());
		// ���̺� ���� �ε����� ���
		lblIndex.setText(String.format("%d", idx + 1));
	}

	// ������
	public MemberVeiw() {
		centerDisplay();
		southDisplay();
		setBounds(400, 400, 600, 700);
		setTitle("ȸ������");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	// �߾� �г�
	private void centerDisplay() {
		JPanel centerPanel = new JPanel();
		lblId = new JLabel("���̵�", JLabel.RIGHT);

		tfId = new JTextField();
		lblNickname = new JLabel("�г�", JLabel.RIGHT);
		tfNickname = new JTextField();
		lblPw = new JLabel("��й�ȣ", JLabel.RIGHT);
		tfPw = new JTextField();
		lblEmail = new JLabel("�̸���", JLabel.RIGHT);
		tfEmail = new JTextField();
		lblPhone = new JLabel("��ȭ��ȣ", JLabel.RIGHT);
		tfPhone = new JTextField();
		lblBrithday = new JLabel("����", JLabel.RIGHT);
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

	// ���� �г�
	private void southDisplay() {
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 5, 7, 7));
		panel2.setBorder(new TitledBorder("��ȸ"));
		btnFrist = new JButton("ó������");
		btnFrist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ù��° ������ �Դϴ�.", "��ȸ", JOptionPane.INFORMATION_MESSAGE);
				idx = 0;
				printData();

			}

		});
		btnPrev = new JButton("����");
		btnNext = new JButton("����");
		btnLast = new JButton("����������");

		lblIndex = new JLabel("�ε���", JLabel.CENTER);

		panel2.add(btnFrist);
		panel2.add(btnPrev);
		panel2.add(lblIndex);
		panel2.add(btnNext);
		panel2.add(btnLast);

		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(1, 5, 7, 7));
		panel3.setBorder(new TitledBorder("�۾�"));

		btnInsert = new JButton("����");
		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// lblIndex�� �ؽ�Ʈ �� ������ �ƴϸ� �޼��� �ڽ��� ����ϰ� �Լ��� ������ ����
				// ���ڿ��� ���ϼ� ���δ� == �ƴϰ� equals
				if (lblIndex.getText().equals("����") == false) {
					JOptionPane.showMessageDialog(null, "����� ������.", "���Կ���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// �Է��� �����͸� ��������
				String id = tfId.getText();
				String nickname = tfNickname.getText();
				String pw = tfPw.getText();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				String birthday = tfBirthday.getText();

				// �Է��� ������ ��ȿ���˻�.
				// 1. �Է��� ���̵� �ʼ� �Է��̰� 10�ڸ��̻� ���� �˻�
				if (id.trim().length() > 10) {
					JOptionPane.showMessageDialog(null, "10���̳��� �Է��ϼ���.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
				}

				// 2. �Է��� ��ȭ��ȣ�� �ʼ� �Է�, ���μ���, 11�ڸ�
				if (phone.trim().length() != 11) {
					JOptionPane.showMessageDialog(null, "11�ڷ� �Է��ϼ���.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
				}
				int i = 0;
				while (i < phone.length()) {
					char ch = phone.charAt(i);
					if (ch < '0' || ch > '9')
						break;
					i = i + 1;
				}
				// birthday yyyy-mm-dd �������� �ԷµǾ��ٰ� �����Ҷ� �ڷ��� ����.
				int year = Integer.parseInt(birthday.substring(0, 4));
				int month = Integer.parseInt(birthday.substring(5, 7));
				int day = Integer.parseInt(birthday.substring(8, 10));
				Calendar cal = Calendar.getInstance();
				cal.set(year, month - 1, day);
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
				int idx = list.size() - 1;

			}

		});

		btnUpdate = new JButton("����");
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �Է��� �ؽ�Ʈ ���� ��������
				String id = tfId.getText();
				String Nickname = tfNickname.getText();
				String pw = tfPw.getText();
				String email = tfEmail.getText();
				String phone = tfPhone.getText();
				String birthday = tfBirthday.getText();

				// ID�� �����Ͱ� ��������� �޼��� �ڽ��� ����ϰ� �����ϰ� ����ϱ�
				if (id.trim().length() < 1) {
					JOptionPane.showMessageDialog(null, "���̵�� �ʼ��Դϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// password�� �����Ͱ� ��������� �޼��� �ڽ� ����ϰ� �����ϰ� ����ϱ�
				if (pw.trim().length() < 1) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �ʼ��Դϴ�.", "�Է¿���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// ��¥ �����Ͱ� �⵵4�ڸ�-��2�ڸ�-��2�ڸ� �������� ����
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
					JOptionPane.showMessageDialog(null, "��������� YYYY-MM-DD �������� �Է����ּ���.");
					return;
				}
			}

		});
		btnDelete = new JButton("����");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int r = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
				System.out.println(r);
				if (r == 0) {
					JOptionPane.showMessageDialog(null, "��������", "����", JOptionPane.INFORMATION_MESSAGE);
				}
				dao.deleteMember(tfId.getText(), tfPw.getText());
			}

		});

		btnSearch = new JButton("��ȸ");
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
				JOptionPane.showMessageDialog(null, "�ش� ȸ���� �����ϴ�. ���̵� Ȯ���غ�����.");
			}

		});

		btnClear = new JButton("����");
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �ؽ� �ʵ���� �ؽ�Ʈ�� ���� ����
				tfId.setText("");
				tfNickname.setText("");
				tfPw.setText("");
				tfEmail.setText("");
				tfPhone.setText("");
				tfBirthday.setText("");

				// lblIndex�� Ÿ��Ʋ�� �������� ����
				lblIndex.setText("����");
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
