package question;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.ImagePanel;
import home.HomeFrame;
import result.ResultFrame;
import user.User;

/**
 * 책 추천 질문 화면 클래스
 */

public class QuestionFrame extends JFrame {
	JLabel questionLbl = new JLabel();
	JPanel currentPagePanel; // 현재 질문 패널을 저장하는 변수
	JPanel nextPagePanel; // 다음 질문 패널을 저장하는 변수
	ImagePanel questionFrameImg = new ImagePanel(new ImageIcon("./image/질문화면.png").getImage());
	
	String selectedGenre; // 선택한 장르 변수
	String selectedSubGenre; // 선택한 서브장르 변수
	String selectedPage; // 선택한 페이지 변수
	int selectedSchool; // 선택한 학교보유여부 변수
	
    private User currentUser;

	public QuestionFrame(User currentUser) {
        this.currentUser = currentUser;
        
		setTitle("질문 화면");
		setSize(1015, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		questionFrameImg.setLayout(null);

		questionLbl.setText("1. 어떤 장르 좋아해?");
		questionLbl.setFont(new Font("", Font.BOLD, 25));
		questionLbl.setBounds(100, 5, 900, 200);
		questionFrameImg.add(questionLbl);

		JPanel genrePanel = genrePanel();
		questionFrameImg.add(genrePanel);

		currentPagePanel = genrePanel;

		setLocationRelativeTo(null);
		add(questionFrameImg);
		pack();

	}

	// 장르 패널
	private JPanel genrePanel() {
		Map<String, String> genreMap = new LinkedHashMap<>();
		genreMap.put("소설", "novel");
		genreMap.put("전공", "major");
		genreMap.put("자기계발", "improve");
		genreMap.put("에세이", "essay");

		JPanel genrePanel = new JPanel();
		genrePanel.setLayout(new FlowLayout());

		for (String genre : genreMap.keySet()) {
			JButton button = new JButton(genre);

			button.setFont(new Font("", Font.PLAIN, 18));
			button.setPreferredSize(new Dimension(160, 60));

			button.addActionListener(e -> {
				selectedGenre = genreMap.get(genre); // 선택된 장르 업데이트

				updateQuestionText("2. 어떤 분야의 책을 좋아해?");
				showNextQuestionPanel(subGenrePanel());
			});

			genrePanel.add(button);
		}

		genrePanel.setBackground(Color.white);
		genrePanel.setBounds(100, 200, 800, 100);

		return genrePanel;
	}

	// 서브 장르 패널
	private JPanel subGenrePanel() {
		Map<String, String> subGenreMap = new LinkedHashMap<>();

		if ("novel".equals(selectedGenre)) {
			subGenreMap.put("로맨스", "로맨스");
			subGenreMap.put("추리", "추리");
			subGenreMap.put("역사", "역사");
			subGenreMap.put("고전", "고전");
			subGenreMap.put("일상", "일상");
		} else if ("major".equals(selectedGenre)) {
			subGenreMap.put("디자인", "디자인");
			subGenreMap.put("프론트엔드", "프론트엔드");
			subGenreMap.put("백엔드", "백엔드");
		} else if ("improve".equals(selectedGenre)) {
			subGenreMap.put("인간관계", "인간관계");
			subGenreMap.put("능력", "능력");
			subGenreMap.put("성공", "성공");
		} else if ("essay".equals(selectedGenre)) {
			subGenreMap.put("캐릭터", "캐릭터");
			subGenreMap.put("사랑", "사랑");
			subGenreMap.put("치유", "치유");
		}

		JPanel subGenrePanel = new JPanel();
		subGenrePanel.setLayout(new FlowLayout());

		for (String subGenre : subGenreMap.keySet()) {
			JButton button = new JButton(subGenre);

			button.setFont(new Font("", Font.PLAIN, 18));
			button.setPreferredSize(new Dimension(130, 60));

			button.addActionListener(e -> {
				selectedSubGenre = subGenreMap.get(subGenre);

				updateQuestionText("3. 길이는 어느 정도가 좋아?");
				showNextQuestionPanel(PagePanel());
			});

			subGenrePanel.add(button);
		}

		subGenrePanel.setBounds(100, 200, 800, 100); // 위치 조정
		subGenrePanel.setBackground(Color.white);
		questionFrameImg.add(subGenrePanel);

		return subGenrePanel;
	}

	// 쪽수 패널
	private JPanel PagePanel() {
		// 그냥 hashmap은 순서가 없기 때문에, 삽입한 순서대로 사용하기 위해 LinkedHashMap을 사용
		Map<String, String> pageMap = new LinkedHashMap<>();
		pageMap.put("200쪽 미만", "< 200");
		pageMap.put("200~500쪽", "BETWEEN 200 AND 500");
		pageMap.put("500쪽 초과", "> 500");

		JPanel pagePanel = new JPanel();
		pagePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

		for (String page : pageMap.keySet()) {
			JButton button = new JButton(page);

			button.setFont(new Font("", Font.PLAIN, 18));
			button.setPreferredSize(new Dimension(160, 60));

			button.addActionListener(e -> {
				selectedPage = pageMap.get(page);

				updateQuestionText("4. 학교 도서관에 있는 책이면 좋겠어?");
				showNextQuestionPanel(schoolPanel());
			});

			pagePanel.add(button);
			questionFrameImg.add(pagePanel);
		}

		pagePanel.setBackground(Color.white);
		pagePanel.setBounds(100, 200, 800, 100);

		return pagePanel;
	}

	// 학교보유여부 패널
	private JPanel schoolPanel() {
		Map<String, Integer> schoolMap = new LinkedHashMap<>();
		schoolMap.put("응! 학교 도서관에 있으면 좋겠어", 1);
		schoolMap.put("아니 상관없어", 0);

		JPanel schoolPanel = new JPanel();
		schoolPanel.setLayout(new FlowLayout());

		for (String school : schoolMap.keySet()) {
			JButton button = new JButton(school);
			// schoolBtns.add(button);

			button.setFont(new Font("", Font.PLAIN, 18));
			button.setPreferredSize(new Dimension(300, 40));

			button.addActionListener(e -> {
				selectedSchool = schoolMap.get(school);
				showNextQuestionPanel(null);
			});

			schoolPanel.add(button);
			questionFrameImg.add(schoolPanel);
		}

		schoolPanel.setBounds(100, 200, 800, 100);
		schoolPanel.setBackground(Color.white);

		return schoolPanel;
	}

	// 패널을 제거하는 메서드
	private void removePanel(JPanel panelToRemove) {
		questionFrameImg.remove(panelToRemove);
		questionFrameImg.validate();
		questionFrameImg.repaint();
	}

	// 다음 질문 패널을 가져오는 메서드
	private void showNextQuestionPanel(JPanel nextPanel) {

		removePanel(currentPagePanel);

		if (nextPanel != null) {
			// 다음 질문 패널 보이기
			nextPanel.setVisible(true);
			// 현재 질문 패널 업데이트
			currentPagePanel = nextPanel;
		} else {
			// 모든 질문이 끝났을 때 필요한 로직 추가
			System.out.println("모든 질문이 완료되었습니다.");

			setVisible(false);
			new ResultFrame(selectedGenre, selectedSubGenre, selectedPage, selectedSchool, currentUser);
		}
	}

	// 질문 텍스트를 업데이트하는 메서드
	private void updateQuestionText(String newText) {
		questionLbl.setText(newText);
	}

}