package home;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import common.CommonFrame;
import common.ImagePanel;
import question.QuestionFrame;
import recommend.RecommendFrame;
import user.User;

/**
 * 홈 화면 클래스
 */

public class HomeFrame extends JFrame {
	private User currentUser;
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public HomeFrame(User user) {
		this.currentUser = user;

		setTitle("홈 화면");
		setSize(1000, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImagePanel homeFrameImg = new ImagePanel(new ImageIcon("./image/메인화면.png").getImage());
		homeFrameImg.setLayout(null);

		JButton recommandedBookBtn = new JButton(); // 책 추천받기 버튼
		recommandedBookBtn.setBounds(120, 244, 270, 100);
		recommandedBookBtn.setOpaque(false); // 버튼의 배경을 투명하게 설정
		recommandedBookBtn.setContentAreaFilled(false); // 내용 영역 채우기 안함
		recommandedBookBtn.setBorderPainted(false); // 테두리 없음
		homeFrameImg.add(recommandedBookBtn);

		JButton recommandBookBtn = new JButton(); // 책 추천하기 버튼
		recommandBookBtn.setBounds(120, 376, 270, 100);
		recommandBookBtn.setOpaque(false);
		recommandBookBtn.setContentAreaFilled(false);
		recommandBookBtn.setBorderPainted(false);
		homeFrameImg.add(recommandBookBtn);

		recommandedBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuestionFrame(user).setVisible(true);
				setVisible(false);
			}
		});

		recommandBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RecommendFrame().setVisible(true);
			}
		});

		JLabel nameLbl = new JLabel(currentUser.getId() + " 님");
		nameLbl.setFont(new Font("", Font.BOLD, 25));
		nameLbl.setBounds(705, 190, 200, 100);
		homeFrameImg.add(nameLbl);

		String userBookStr = "";
		String checkQuery = "SELECT user_book FROM user WHERE user_identi = ?";
		ResultSet resultSet = CommonFrame.getResult(checkQuery, currentUser.getId());
		try {
		    if (resultSet.next()) {
		        userBookStr = resultSet.getString("user_book");
		        System.out.println("첵 제목: " + userBookStr);
		    } else {
		        System.out.println("아직 추천받은 책이 없습니다");
		    }
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}


		JLabel userBookLbl = new JLabel(userBookStr);
		userBookLbl.setFont(new Font("", Font.BOLD, 25));
		userBookLbl.setBounds(580, 350, 400, 100);
		homeFrameImg.add(userBookLbl);

		setLocationRelativeTo(null);
		add(homeFrameImg);

	}

}