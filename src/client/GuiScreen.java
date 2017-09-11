package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.IconUIResource;
import javax.swing.text.AbstractDocument.Content;
import javax.swing.text.IconView;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import sever.ServerThread;
import vo.Board;
import vo.User;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Scrollbar;
import java.awt.Shape;
import java.awt.Toolkit;

public class GuiScreen extends JFrame implements ActionListener {

	JPanel contentPane;
	JTextField tfId;
	JPasswordField tfpw;
	JList list;
	// 버튼 쪽
	JButton addPostingBu;
	JButton deletePostingBu;
	JButton editPostingBu;
	JButton findUserBu;
	JButton showListBu;
	JButton checkbu;
	JButton cancleBu;
	JButton exitBu;
	// login 쪽
	JButton addUserBu;
	JButton userdelBu;
	JButton loginBu;
	JButton logoutBu;
	String checkStatus = "find";
	ClientManager manager = new ClientManager();
	private Scrollbar scrollbar;
	Board board = null;
	User user;
	private JTextField serchtf;

	GuiScreen frame;
	GuiBoard guiboard;

	Calendar d = Calendar.getInstance();
	int year = d.get(Calendar.YEAR);
	int month = d.get(Calendar.MONTH)+1;
	int day = d.get(Calendar.DATE);
	String todate = year + "." + month + "." + day;
	ArrayList<Board> blist;
	int num1 = 1;
	int num2 = 0;
	String id;
	boolean logresult;
	private JPanel panel_3;
	private JLabel label;
	private JPanel panel_4;
	private JButton musicstar;
	private JButton musicsto;
	Music1 m;
	String fff= "forest.jpg";
	ImageIcon ic = new ImageIcon(fff);

	

	


	// 클릭 두번에 창뜨게 하는 것과 선택한 것을 클릭 2번하면 데이터를 다른 창에 담아주는 역할
	// 클쓰기 누르면 창뜨는 것과 등록시에 아이디 보내기.
	public GuiScreen(){
		
		
			
		frame = this;
		this.frame.setVisible(true);

		setTitle("\uC8FC\uB9D0\uC740 in \uD558\uC790\uB9C8\uC790 out");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel westPanel = new JPanel();
		JPanel panel_1 = new JPanel();
		JButton idBu = new JButton("ID");
		contentPane.add(westPanel, BorderLayout.WEST);
		westPanel.add(panel_1);
		panel_1.add(idBu);
		westPanel.setLayout(new GridLayout(2, 0, 0, 1));
		panel_1.setLayout(new GridLayout(2, 0, 0, 2));
		idBu.setFont(new Font("굴림", Font.BOLD, 20));
		tfId = new JTextField();
		tfId.setFont(new Font("굴림", Font.BOLD, 20));
		tfId.setColumns(10);
		panel_1.add(tfId);

		JButton pw = new JButton("PassWord");
		pw.setFont(new Font("굴림", Font.BOLD, 20));
		panel_1.add(pw);

		tfpw = new JPasswordField();
		tfpw.setFont(new Font("굴림", Font.BOLD, 20));
		tfpw.setColumns(10);
		panel_1.add(tfpw);

		JPanel panel_2 = new JPanel();
		westPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 4));

		addUserBu = new JButton("등 록");
		userdelBu = new JButton("삭 제");
		loginBu = new JButton("로그인");
		
		logoutBu = new JButton("로그아웃");
		panel_2.add(addUserBu);
		panel_2.add(userdelBu);
		panel_2.add(loginBu);
		panel_2.add(logoutBu);
		
		userdelBu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				String pw = tfpw.getText();
				boolean deleteresult = false;
				if (e.getSource().equals(userdelBu)) {
					if (tfId.getText().isEmpty() && tfpw.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "삭제할 아이디와 비밀번호를 적어주세요.");
					} else if (tfId.getText().isEmpty() || tfpw.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "삭제할 아이디나 비밀번호가 틀립니다.");
					} else {
						deleteresult = manager.delete(id, pw);
						if (deleteresult == true) {
							manager.userDelete(id);
							JOptionPane.showMessageDialog(null, "삭제 성공");
							tfpw.setText("");
							tfId.setText("");
							exceptLoginClose();
						} else if (deleteresult == false) {
							JOptionPane.showMessageDialog(null, "[삭제실패]비밀번호나 아이디가 틀렸습니다. ");
						}
					}
				}
			}
		});

		addUserBu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				String pw = tfpw.getText();
			
				User u = new User(id, pw);
				boolean addresult = false;
				if (e.getSource().equals(addUserBu)) {
					if (tfId.getText().isEmpty() && pw.isEmpty()) {
						JOptionPane.showMessageDialog(null, "위의 아이디와 비밀번호를 입력하고 등록해주세요.");
					} else if (tfId.getText().isEmpty() || pw.isEmpty()) {
						JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 입력해주세요.");
					} else {
						addresult = manager.userAdd(u);
						if (addresult == true) {
							JOptionPane.showMessageDialog(null, "등록 성공입니다.");
						} else {
							JOptionPane.showMessageDialog(null, "등록된 아이디입니다.");
						}
					}
				}
			}
		});

		loginBu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// logresult =false;
				String idd123 = tfId.getText();
				String pw = tfpw.getText();
				if (e.getSource().equals(loginBu)) {
					if (tfId.getText().isEmpty() && tfpw.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "아이디가 없으시면 등록해주시고, 있으시면 아이디와 비밀번호를 입력해주세요.");
					} else if (tfId.getText().isEmpty() || tfpw.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "아이디나 비밀번호를 입력해주세요.");
					} else {
						logresult = manager.login(idd123, pw);
						if (logresult == true) {
							JOptionPane.showMessageDialog(null, "로그인 성공");
							tfId.setEditable(false);
							tfpw.setEditable(false);
							tfpw.setText("");
							allOpen();
							
							
							
						} else if (logresult == false) {
							JOptionPane.showMessageDialog(null, "로그인 실패");
						}
					}
				}
			}
		});
		logoutBu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(logoutBu)) {
					exceptLoginClose();
					tfId.setText("");
					tfpw.setText("");
					tfId.setEditable(true);
					tfpw.setEditable(true);
			
				}
			}
		});
		
		list = new JList();
		list.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Board LIst (게시글 두번 클릭시 수정 기능)",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		list.setFont(new Font("굴림", Font.BOLD, 15));
		list.setVisibleRowCount(1);
//		contentPane.add(list, BorderLayout.CENTER);
//		list.paintComponents(Toolkit.getDefaultToolkit().getImage(fff));----------------------------------------------------------------------------------
		JScrollPane jap = new JScrollPane(list);
		
		contentPane.add(jap, BorderLayout.CENTER);

			ArrayList<Board> bolist = manager.intoTheList();
			list.setListData(bolist.toArray());

		
		panel_4 = new JPanel();
		jap.setColumnHeaderView(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		musicstar = new JButton("\uC74C\uC545\uC2DC\uC791");
		musicstar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m = new Music1();
				m.start();
			}
		});
		panel_4.add(musicstar);
		
		musicsto = new JButton("\uC74C\uC545 \uC911\uC9C0");
		panel_4.add(musicsto);
		musicsto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				m.stop();
			}
		});
		
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {// ---------------------------
													// 고칠부분
				int x = e.getClickCount();
				if (x == 2) {
					board = (Board) list.getSelectedValue();
					if (board != null) {
						guiboard = new GuiBoard(frame);
						toBack();
						guiboard.register.setEnabled(false);
						guiboard.tfbbnumber.setEditable(false);
						guiboard.tfdate.setEditable(false);
						guiboard.tfbname.setEditable(false);
						guiboard.tfbname.setText(board.getuId());
						guiboard.tfbbnumber.setText(new Integer(board.getNum()).toString());
						guiboard.tfdate.setText(todate);
						guiboard.tatext.setText(board.getText());
						guiboard.tftitle.setText(board.getTitle());
						board = null;
						editPosting();
					} else {
						JOptionPane.showMessageDialog(null, "로그인 하세요");
					}
				} else {
					board = (Board) list.getSelectedValue();
				}
				super.mouseClicked(e);
			}
		});

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(2, 0, 0, 8));

		addPostingBu = new JButton("글쓰기");
		deletePostingBu = new JButton("글 삭제");
		findUserBu = new JButton("작성자 검색");
		showListBu = new JButton("전체 목록");
		checkbu = new JButton("확 인");
		cancleBu = new JButton("취 소");
		exitBu = new JButton("종 료");
		panel.add(addPostingBu);
		panel.add(deletePostingBu);
		panel.add(showListBu);
		panel.add(exitBu);

		deletePostingBu.addActionListener(new ActionListener() { /// 지우는 곳
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = 0;
				boolean dersult = false;
				boolean result = false;
				if (e.getSource().equals(deletePostingBu)) {
					if (tfpw.getText().isEmpty()) {
						tfpw.setEditable(true);
						JOptionPane.showMessageDialog(null, "비밀번호를 한번 더 입력해주세요.");
						list.setEnabled(false);
						checkStatus = "deletpp2";
					} else if ("deletpp2".equals(checkStatus)) {
						JOptionPane.showMessageDialog(null, "목록 선택 후 한번더 삭제 버튼을 눌러주세요");
						list.setEnabled(true);
						boolean xx = board != null;
						if(e.getSource().equals(deletePostingBu)){
							if(xx == false)
							JOptionPane.showMessageDialog(null, "리스트를 선택해주세요");	
						}
						checkStatus = "deletpp3";
					} else if (checkStatus.equals("deletpp3")) {
						String id = tfId.getText();
						String pw = tfpw.getText();
						result = manager.login(id, pw);
						if (result == true) {
							x = board.getNum();
							dersult = manager.boardDelete(x);
							if (dersult == true) {
								JOptionPane.showMessageDialog(null, "삭제 성공");
								ArrayList<Board> bb1 = manager.intoTheList();
								list.setListData(bb1.toArray());
								board = null;
								tfpw.setEditable(false);
								tfpw.setText("");
								checkStatus = null;
							} else {
								JOptionPane.showMessageDialog(null, "삭제 실패");
								board = null;
								tfpw.setEditable(false);
								tfpw.setText("");
							}
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
						}
					}
				}
				// }
			}
		});

		cancleBu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cancleBu)) {
					if (checkStatus.equals("deletpp2") || checkStatus.equals("deletpp3")) {
						tfpw.setText("");
						tfpw.setEditable(false);
					} else {
						serchtf.setText("");
					}
				}
			}
		});

		showListBu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Board> bb = manager.intoTheList();
				if (e.getSource().equals(showListBu)) {
					list.setListData(bb.toArray());
				}
			}
		});

		addPostingBu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // --------- 누르면 창은 뜨지만
															// 메니져가 겹처서 힘듬 수정
				if (e.getSource().equals(addPostingBu)) {
					guiboard = new GuiBoard(frame);
					toBack();
					guiboard.tfbbnumber.setEditable(false);
					guiboard.tfdate.setEditable(false);
					guiboard.tfbname.setEditable(false);
					guiboard.tfdate.setText(todate);

					num2 = manager.countNum(num1);
					guiboard.tfbbnumber.setText(new Integer(num2).toString());
					guiboard.tfbbnumber.setEditable(false);
					guiboard.tfbname.setText(tfId.getText());

					addPosting();
				}

			}
		});

		panel.add(findUserBu);
		serchtf = new JTextField();
		panel.add(serchtf);
		serchtf.setColumns(10);
		panel.add(checkbu);
		panel.add(cancleBu);
		panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		label = new JLabel("\uC219\uC81C \uAC8C\uC2DC\uD310");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.BOLD, 20));
		panel_3.add(label);
		
		cancleBu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource().equals(cancleBu)) {
					serchtf.setText("");
				}
			}
		});

		checkbu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 검색부분 고쳐야함.
				ArrayList<Board> bb2 = null;
				System.out.println(todate);
				String xxxx = serchtf.getText();
				if (e.getSource().equals(checkbu)) {
					if (serchtf.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "검색할 정보를 입력해주세요(아이디, 번호, 날짜)");
					} else if (checkStatus.equals("find")) {
						bb2 = manager.samethingsBoardShow(xxxx);
						list.setListData(bb2.toArray());
					}

				}
			}
		});
		exceptLoginClose();

		exitBu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

	public void allOpen() {
		list.setEnabled(true);
		addPostingBu.setEnabled(true);
		deletePostingBu.setEnabled(true);
		findUserBu.setEnabled(true);
		showListBu.setEnabled(true);
		checkbu.setEnabled(true);
		cancleBu.setEnabled(true);
		serchtf.setEnabled(true);
	}

	public void exceptLoginClose() {
		list.setEnabled(false);
		addPostingBu.setEnabled(false);
		deletePostingBu.setEnabled(false);
		findUserBu.setEnabled(false);
		showListBu.setEnabled(false);
		checkbu.setEnabled(false);
		cancleBu.setEnabled(false);
		serchtf.setEnabled(false);
	}

	public void addPosting() {

		guiboard.update.setEnabled(false);
		guiboard.register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password1 = guiboard.tfbpw.getText();
				String id = guiboard.tfbname.getText();
				boolean check = false;
				if (e.getSource().equals(guiboard.register)) {
					check = manager.login(id, password1);
					if (guiboard.tftitle.getText().isEmpty() || guiboard.tftitle.getText().isEmpty()
							|| guiboard.tfbpw.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "모든 내용을 채워주십시오.");
					} else if (check == true) {
						int num = new Integer(guiboard.tfbbnumber.getText());
						String title = guiboard.tftitle.getText();
						String date = guiboard.tfdate.getText();
						String text = guiboard.tatext.getText();
						Board bb = new Board(id, text, title, date, num);
						boolean result = manager.boardAdd(bb);
						if (result == true) {
							ArrayList<Board> bb1 = manager.intoTheList();
							list.setListData(bb1.toArray());
							JOptionPane.showMessageDialog(null, "등록성공");

						} else {
							JOptionPane.showMessageDialog(null, "등록실패");
						}
					} else if (check == false) {
						JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
					}
				}
			}
		});
	}

	public void editPosting() {

		guiboard.update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean result = false;
				boolean resultt = false;
				String idd = guiboard.tfbname.getText();
				String pww = guiboard.tfbpw.getText();
				Board bbb = null;
				if (e.getSource().equals(guiboard.update)) {
					if (guiboard.tfbpw.getText().isEmpty() || guiboard.tftitle.getText().isEmpty()
							|| guiboard.tatext.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "모든 내용을 입력해 주세요");
					} else {
						result = manager.login(idd, pww);
						if (result == true) {
							String iid = guiboard.tfbname.getText();
							String tiltee = guiboard.tftitle.getText();
							int numm = new Integer(guiboard.tfbbnumber.getText());
							String textt = guiboard.tatext.getText();
							String datee = todate;
							bbb = new Board(iid, textt, tiltee, datee, numm);
							resultt = manager.updateBoard(bbb);
							if (resultt == true) {
								ArrayList<Board> bb1 = manager.intoTheList();
								list.setListData(bb1.toArray());
								JOptionPane.showMessageDialog(null, "수정 성공");
							} else {
								JOptionPane.showMessageDialog(null, "수정 실패");
							}
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다.");
						}
					}
				}
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
