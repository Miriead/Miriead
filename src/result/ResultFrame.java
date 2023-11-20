package result;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import common.CommonFrame;
import common.ImagePanel;
import home.HomeFrame;
import main.MainFrame;
import user.User;

/**
 * 책 추천 결과 화면 클래스
 */

public class ResultFrame extends JFrame {
	List<BookResult> results = new ArrayList<>();
	ImagePanel resultFrameImg;
	
    private User currentUser;
	
	public ResultFrame(String selectedGenre, String selectedSubGenre, String selectedPage, int selectedSchool, User currentUser) {
        this.currentUser = currentUser;
        
		setTitle("결과 화면");
		setSize(1000, 720);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		resultFrameImg = new ImagePanel(new ImageIcon("./image/결과화면.png").getImage());
		resultFrameImg.setLayout(null);

		results = resultBook(selectedGenre, selectedSubGenre, selectedPage, selectedSchool);

		displayResults();

		add(resultFrameImg);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private List<BookResult> resultBook(String selectedGenre, String selectedSubGenre, String selectedPage,
			int selectedSchool) {
		List<BookResult> resultList = new ArrayList<>();

		String checkQuery = "SELECT * FROM " + selectedGenre + " WHERE " + selectedGenre + "_type = ? AND "
				+ selectedGenre + "_page " + selectedPage + " AND " + selectedGenre + "_school = ?";

		ResultSet resultSet = CommonFrame.getResult(checkQuery, selectedSubGenre, selectedSchool);

		try {
			while (resultSet.next()) {
				int id = resultSet.getInt(selectedGenre + "_id");
				String title = resultSet.getString(selectedGenre + "_title");
				String author = resultSet.getString(selectedGenre + "_author");
				int page = resultSet.getInt(selectedGenre + "_page");
				int school = resultSet.getInt(selectedGenre + "_school");
				String type = resultSet.getString(selectedGenre + "_type");
				String story = resultSet.getString(selectedGenre + "_story");

				BookResult result = new BookResult(id, title, author, page, school, type, story);
				resultList.add(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close(); // ResultSet 닫기
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultList;
	}

	private void displayResults() {
		if (results.isEmpty()) {
			JLabel resultEmpryLbl = new JLabel("조건에 맞는 책이 없습니다.");
			resultEmpryLbl.setFont(new Font("SansSerif", Font.BOLD, 25));
			resultEmpryLbl.setBounds(60, 40, 700, 50);
			resultFrameImg.add(resultEmpryLbl);
		} else {
			// 결과 목록을 무작위로 섞기
			Collections.shuffle(results);

			// 첫 번째 결과만 선택
			BookResult result = results.get(0);

			// 화면에 결과 보이게 하기
			JLabel titleLabel = new JLabel("제목: " + result.getTitle());
			titleLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
			titleLabel.setBounds(60, 40, 700, 50);
			resultFrameImg.add(titleLabel);
			
			String updateQuery = "UPDATE user SET user_book = ? WHERE user_identi = ?";
			CommonFrame.updateSQL(updateQuery, result.getTitle(), currentUser.getId());

			JLabel authorLabel = new JLabel("저자: " + result.getAuthor());
			authorLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
			authorLabel.setBounds(60, 100, 700, 50);
			resultFrameImg.add(authorLabel);

			JLabel pageLabel = new JLabel("쪽수: " + result.getPage());
			pageLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
			pageLabel.setBounds(60, 160, 700, 50);
			resultFrameImg.add(pageLabel);

			JLabel schoolLabel = new JLabel();

			if (result.getSchool() == 1) {
				schoolLabel.setText("학교보유여부: 있음");
			} else {
				schoolLabel.setText("학교보유여부: 없음");
			}

			schoolLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
			schoolLabel.setBounds(60, 220, 300, 50); // 텍스트 크기에 맞게 수정
			resultFrameImg.add(schoolLabel);

			JLabel typeLabel = new JLabel("타입: " + result.getType());
			typeLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
			typeLabel.setBounds(60, 280, 200, 30);
			resultFrameImg.add(typeLabel);

			// 줄거리 보기 버튼
			JButton stroyBtn = new JButton();
			stroyBtn.setBounds(640, 410, 280, 100);
			stroyBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String story = result.getStory();

					// 일정 글자 수가 넘어가면 줄바꿈을 하도록 처리
					int lineLength = 100;
					StringBuilder formattedStory = new StringBuilder("<html>");

					for (int i = 0; i < story.length(); i += lineLength) {
						int endIndex = Math.min(i + lineLength, story.length());
						formattedStory.append(story.substring(i, endIndex)).append("<br>");
					}

					formattedStory.append("</html>");

					JOptionPane.showMessageDialog(null, formattedStory.toString(), "줄거리",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
			stroyBtn.setOpaque(false);
			stroyBtn.setContentAreaFilled(false);
			stroyBtn.setBorderPainted(false);
			resultFrameImg.add(stroyBtn);

			// 프레임을 다시 그리기
			resultFrameImg.revalidate();
			resultFrameImg.repaint();
		}
		
		JButton homebackBtn = new JButton();
		homebackBtn.setBounds(640, 555, 280, 100);
		homebackBtn.setBorder(BorderFactory.createEmptyBorder());
		homebackBtn.setOpaque(false); // 투명 배경
		homebackBtn.setBackground(new Color(30, 144, 255));
		resultFrameImg.add(homebackBtn);

		homebackBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new HomeFrame(currentUser).setVisible(true);
		        dispose(); // 현재 ResultFrame 닫기
			}
		});
	}

}
