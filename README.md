# Miriead
[2023 JAVA 프로젝트] Miriead (미리드)<br>
: 미림 학생들에게 원하는 조건에 맞는 책을 추천해주는 프로그램
<br>
<br>
### 페이지 및 기능 소개
#### 로그인 화면
<img src="https://github.com/phinsso/My-Travel-Journal/assets/110121149/3703a507-7e5a-46dd-9e5a-6eea4ab7407a">
- id와 pw를 입력하면 db의 user 테이블에 존재하는 회원인지 확인 후 로그인 처리

#### 홈 화면
<img src="https://github.com/phinsso/My-Travel-Journal/assets/110121149/be4c4dc0-1e17-4f3e-ac73-65842ee303a0">
- 최근 추천받은 책 확인 가능

#### (책 추천 받기) 질문 화면
<img src="https://github.com/phinsso/My-Travel-Journal/assets/110121149/16b38847-6fec-4acd-8d8f-7719da2405e6">
- 원하는 장르, 세부 분야, 책의 길이, 학교 도서관 보유 여부 등의 조건을 선택함.

#### 결과 화면
<img src="https://github.com/phinsso/My-Travel-Journal/assets/110121149/789824b5-eeae-4110-8aeb-9010501567fd">
- 제목, 저자, 쪽수, 학교 도서관 보유 여부를 확인할 수 있고, 책의 줄거리 정보를 제공받을 수 있음.

#### (책 추천 하기) 추천 화면
<img src="https://github.com/phinsso/My-Travel-Journal/assets/110121149/51cf71c9-25f6-43d4-9b37-8c823ddc644f">
- 드롭다운 요소에서 추천할 책의 장르를 선택할 수 있음.<br>
- 데이터베이스에 이미 존재하는 책일 경우 저장되지 않고, 새로운 도서일 경우 테이블에 저장됨.

<br>

### 추후 보완할 점
- 데이터베이스 구조 리팩토링<br>
- 외부의 도서 api를 사용하여 더 다양한 도서 정보 제공<br>
- 문항을 구간이 정해진 버튼이 아닌, 사용자가 직접 범위를 설정할 수 있게 하여 더 최적화된 조건 생성
