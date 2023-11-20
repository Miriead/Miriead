package main;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import common.ImagePanel;
import join.JoinFrame;
import user.User;
import home.HomeFrame;
import common.CommonFrame;

public class MainFrame extends JFrame {
	private User currentUser; // 사용자 객체 추가
	
	public MainFrame() {
        setTitle("Miriead");
        setSize(1000, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImagePanel mainFrameImg = new ImagePanel(new ImageIcon("./image/시작화면.png").getImage());
        mainFrameImg.setLayout(null);
        
        JTextField idField = new JTextField();
        idField.setBounds(300, 277, 280, 60); // 위치와 크기 설정
        idField.setBorder(BorderFactory.createEmptyBorder()); // 외곽선 없음
        idField.setOpaque(false); // 투명 배경
        idField.setFont(new Font("SansSerif", Font.PLAIN, 18)); // 폰트 설정
        mainFrameImg.add(idField);
        
        JTextField pwField = new JPasswordField();
        pwField.setBounds(300, 377, 280, 60);
        pwField.setBorder(BorderFactory.createEmptyBorder());
        pwField.setOpaque(false);
        pwField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        mainFrameImg.add(pwField);
        
        JButton loginBtn = new JButton();
        loginBtn.setBounds(618, 270, 172, 170);
        loginBtn.setBorder(BorderFactory.createEmptyBorder()); // 외곽선 없음
        loginBtn.setOpaque(false); // 투명 배경
        loginBtn.setBackground(new Color(30, 144, 255)); // 배경색 설정
        mainFrameImg.add(loginBtn);
        
        // 로그인 버튼이 눌렸을 때
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String enteredID = idField.getText();
                String enteredPW = pwField.getText();

                // 데이터베이스에서 사용자 정보 가져오기
                List<String> userData = CommonFrame.getColumnDataFromTable("user", "user_identi", "user_pw");

                // 데이터베이스에서 사용자 정보와 입력한 정보 비교
                boolean loginSuccessful = false;
                for (int i = 0; i < userData.size(); i += 2) {
                    String storedID = userData.get(i);
                    String storedPW = userData.get(i + 1);
                    if (storedID.equals(enteredID) && storedPW.equals(enteredPW)) {
                        loginSuccessful = true;
                        currentUser = new User(enteredID, enteredPW);
                        break;
                    }
                }

                if (loginSuccessful) {
                	JOptionPane.showMessageDialog(null, "로그인 되었습니다");
                	new HomeFrame(currentUser).setVisible(true);
                    setVisible(false);
                } else {
                	JOptionPane.showMessageDialog(null, "올바르지 않은 ID 또는 비밀번호입니다. 다시 입력해주세요.");
                }
                
            }
        });
        
        JButton joinBtn = new JButton();
        joinBtn.setBounds(746, 565, 185, 100);
        joinBtn.setBorder(BorderFactory.createEmptyBorder());
        joinBtn.setOpaque(false); // 투명 배경
        joinBtn.setBackground(new Color(30, 144, 255));
        mainFrameImg.add(joinBtn);
        
        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JoinFrame();
            }
        });
        
        setLocationRelativeTo(null);
        add(mainFrameImg);
    }

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
        frame.setVisible(true);
	}

}
