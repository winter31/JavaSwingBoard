package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vo.Board;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class GuiBoard extends JFrame {
	JButton register;
	JPanel contentPane;
	JTextField tfbname;
	JTextField tfbpw;
	JTextField tfbbnumber;
	JTextField tftitle;
	JTextField tfdate;
	JButton update;

	JButton goback;
	JTextArea tatext;
	GuiScreen frame;
	GuiBoard frame1;

	// public static void main(String[] args) {
	// GuiBoard frame1= new GuiBoard();
	// frame1.setVisible(true);
	//
	// }
	public GuiBoard(GuiScreen frame) {
		open();
		this.frame = frame;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("\uAC8C \uC2DC \uD310");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(6, 0, 0, 2));

		JButton userNamebu = new JButton("사용자 아이디");
		JButton bPWbu = new JButton("비  밀  번  호");
		JButton bnumberBu = new JButton("글    번호");
		JButton bTitlebu = new JButton("글 타이틀");
		JButton bdatebu = new JButton("날       짜");
		panel_1.add(userNamebu);
		tfbname = new JTextField();
		panel_1.add(tfbname);
		tfbname.setColumns(10);
		panel_1.add(bPWbu);
		tfbpw = new JTextField();
		panel_1.add(tfbpw);
		tfbpw.setColumns(10);
		panel_1.add(bnumberBu);
		tfbbnumber = new JTextField();
		panel_1.add(tfbbnumber);
		tfbbnumber.setColumns(10);
		panel_1.add(bTitlebu);
		tftitle = new JTextField();
		panel_1.add(tftitle);
		tftitle.setColumns(10);
		panel_1.add(bdatebu);
		tfdate = new JTextField();
		panel_1.add(tfdate);
		tfdate.setColumns(10);

		JLabel titlebu = new JLabel("      내                              용");
		panel_1.add(titlebu);
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));
		tatext = new JTextArea();
		panel_2.add(tatext);
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		register = new JButton("등록");
		register.addActionListener(frame);
		update = new JButton("수정");
		

		goback = new JButton("뒤로가기");
		panel_3.add(register);
		panel_3.add(update);
		panel_3.add(goback);

		update.addActionListener(frame);
		goback.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
	}

	public void open() {
		this.setVisible(true);
		int x = Toolkit.getDefaultToolkit().getScreenSize().width;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(1000, 700); // 사이즈를 먼저 잡아줘야 가운데에 잡힘;
		// this.pack();
		int width = (x / 2) - (this.getWidth() / 2);
		int height = (y / 2) - (this.getHeight() / 2);

		this.setLocation(width, height);

		this.getContentPane().setBackground(Color.BLACK);
		this.setName("AirCraft System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
