/*[주석 1 : 과제 설명]
 
 객체지향 프로그래밍 평가과제 (배점 25점)
 학과: 환경학과
 학번: 201900650
 이름: 김동휘
 
 과제 주제: 간단한 로그인 페이지와 로그인 후 접속 페이지 그리고 회원가입 페이지를 구현 하였습니다. 
 
 */
package Main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class programex {

	private JFrame frame;
	private JTextField Idfield;
	private JTextField passfield;
	private JTextField textField;
	private JTextField nIdfield;
	private JTextField npassfield;
	private JTextField cknpassfield;
	private JTextField name;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					programex window = new programex();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public programex() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1179, 757);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setLocationRelativeTo(null);
		ImagePanel loginpanel = new ImagePanel(new ImageIcon("./image/ui.png").getImage()); // 주석 5 : 다형성
		ImagePanel nextpn = new ImagePanel(new ImageIcon("./image/page.png").getImage());
		ImagePanel rgpn = new ImagePanel(new ImageIcon("./image/page.png").getImage());
		
		frame.setSize(new Dimension(741, 696));
		frame.setPreferredSize(loginpanel.getDim());
		frame.getContentPane().add(loginpanel);
		frame.getContentPane().add(nextpn);
		nextpn.setLayout(null);
		frame.getContentPane().add(rgpn);
		rgpn.setLayout(null);
		loginpanel.setVisible(true);
		nextpn.setVisible(false);
		rgpn.setVisible(false);
		
		// 회원가입 버튼 라벨 필드
		JButton btn2 = new JButton("중복확인");
		btn2.setBounds(300, 10, 97, 23);
		rgpn.add(btn2);
		
		JButton btn3 = new JButton("가입하기");
		btn3.setBounds(300, 170, 97, 23);
		rgpn.add(btn3);
		
		JButton btn4 = new JButton("취소하기");
		btn4.setBounds(400, 170, 97, 23);
		rgpn.add(btn4);
		
		nIdfield = new JTextField();
		nIdfield.setBounds(150, 10, 116, 21);
		rgpn.add(nIdfield);
		nIdfield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("아이디");
		lblNewLabel.setBounds(50, 10, 57, 15);
		rgpn.add(lblNewLabel);

		
		npassfield = new JTextField();
		npassfield.setBounds(150, 50, 116, 21);
		rgpn.add(npassfield);
		npassfield.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("비밀번호");
		lblNewLabel2.setBounds(50, 50, 57, 15);
		rgpn.add(lblNewLabel2);
		
		cknpassfield = new JTextField();
		cknpassfield.setBounds(150, 90, 116, 21);
		rgpn.add(cknpassfield);
		cknpassfield.setColumns(10);
		
		JLabel lblNewLabel3 = new JLabel("비밀번호 확인");
		lblNewLabel3.setBounds(50, 90, 100, 15);
		rgpn.add(lblNewLabel3);
		
		name = new JTextField();
		name.setBounds(150, 130, 116, 21);
		rgpn.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel4 = new JLabel("이름");
		lblNewLabel4.setBounds(50, 130, 100, 15);
		rgpn.add(lblNewLabel4);




		// 로그인창 
		JButton loginbutton = new JButton("Log In");
		loginbutton.setBounds(893, 551, 80, 47);
		loginpanel.add(loginbutton);
		JButton rgbutton = new JButton("가입");
		rgbutton.setBounds(893, 500, 80, 47);
		loginpanel.add(rgbutton);
		
		Idfield = new JTextField();
		Idfield.setBounds(657, 505, 166, 23);
		loginpanel.add(Idfield);
		Idfield.setColumns(10);
		
		passfield = new JTextField();
		passfield.setBounds(657, 574, 166, 23);
		loginpanel.add(passfield);
		passfield.setColumns(10);
		// 로그인 버튼 기능
		boolean k = false;
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				idboard lo;
				lo = new login();
				boolean k = lo.check(Idfield.getText(), passfield.getText());
				if (k == true) {
					loginpanel.setVisible(false);
					nextpn.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Fail the password");
				}
				
			}
		});
		// 등록버튼 기능
		rgbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginpanel.setVisible(false);
				nextpn.setVisible(false);
				rgpn.setVisible(true);
			}
		});
		
		//가입버튼 기능
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            try{ // 주석 4 : 예외 처리
                String s = null;
                boolean isOk = false;
                BufferedWriter bw = new BufferedWriter(new FileWriter("./register/members.txt", true)); // 주석 8 : 파일 입출력
                BufferedReader br = new BufferedReader(new FileReader("./register/members.txt"));
                
                List<String> list = new ArrayList<String>(); //  주석 7 : 컬렉션 프레임워크 List
                if(e.getSource() == btn3) {
                    while((s = br.readLine()) != null) {
                    	String[] parts = s.split("/");
                        // 아이디 중복
                        list.addAll(Arrays.asList(parts));
                        for(int i = 0; i< list.size(); i+=3) {
                        if(list.get(i).equals(nIdfield.getText())){
                                isOk = true;
                                break;
                        }
                    }
                    }
                            //정보 입력시 중복이 없으면 데이터 보냄
                            if(!isOk) {
                            bw.write(nIdfield.getText() + "/");
                            bw.write(npassfield.getText() + "/");
                            bw.write(name.getText() + "\r\n");
                            bw.close();
 
                            JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");

                            }else {
                                JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
                            }
                }
            }catch (IOException  ex){
                JOptionPane.showMessageDialog(null, "실패");
            }
			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
            	nIdfield.setText("");
            	npassfield.setText("");
            	cknpassfield.setText("");
                name.setText("");
			}
		}
	);
		// 아이디 중복확인
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(lblNewLabel.getText().equals(null)) {
						lblNewLabel.setText("없음");
					}
					
					String fileURL = "./register/" + lblNewLabel.getText() +
	                "/" + lblNewLabel.getText()+".txt"; 
					File file = new File(fileURL);
					try {
						FileReader fr = new FileReader(file);
						if(file.exists()&&file.isFile()) {
							JOptionPane.showMessageDialog(frame, "중복된 아이디입니다.");
						}
						fr.close();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frame, "사용 가능한 아이디입니다.");
			}
		}
	});


	frame.pack();
}
}