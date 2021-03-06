---

# 기획의도
우리 학교는 안양역에서 통학하는 학생들이 많은데 셔틀버스는 부족하고 택시도 줄을 서서 타야 하는데 차량을 가지고 통학하는 학생,교직원들이 약간의 보상을 받거나 무상으로 오고 가는 길에 같이 탑승하면 서로 경제적, 시간적으로 절약이 될 거라고 생각.
***
 
# 요구사항
###### 필요한 요소 

* **회원가입과 로그인 구현**을 위한 [Firebase](https://firebase.google.com/) 인증기능 연동
* **편리한 로그인**을 위해 페이스북 api를 활용(로그인 기능)
* **실시간 상호작용**을 위해 파이어베이스 실시간 데이터베이스 사용
***

# 레이아웃 구성
직관적인 큰 버튼 위주 레이아웃
버튼 선택에 따른 단방향 ui
***

# 동작과정
* 로그인(페이스북 계정 사용)
* 드라이버, 승객 분기점
* **드라이버 선택 시**
* 상단 드라이버/승객 구분 텍스트 및 회원명
* 차량번호 입력 폼 - 차량번호 입력 시 SharedPreferences에 차량번호 저장
* 경로선택 버튼(차량번호 미입력시 경로선택 불가)
* 경로 선택 시 대기중인 승객 목록 출력(리스트뷰 이용, 이름 출력)

* **승객 선택 시**
* 상단 드라이버/승객 구분 텍스트 및 회원명
* 경로선택 버튼
* 경로 선택 시 대기중인 기사 목록 출력(리스트뷰 이용, 이름/차량번호 출력)
***

# 기대효과 및 활용방안(시장성 및 기대 효과)
* 실제로 외국인과 편리하게 채팅을 할 수 있어 채팅앱으로도 무난함
* 각 나라의 정보(환전소, 여행지)를 알려줌으로 내비게이션 시스템으로도 가능
* SOS를 이용하여 위험 상황시 비상연락망으로 사용할 수 있어 비상시에도 사용	하기 무난함
* 현재 어플 중에는 번역 어플 밖에 없으므로, 언어를 못하거나 여행지역에 대해 잘 몰라도 사용하기 편리하여 시장성으로도 좋다고 본다.
* 친구 추가를 할 시 상대방에게 자신의 위치를 알려 줄 수 있어 서로가 쉽게 찾을 수 있다.
***
### 개발자
| Name | Email |
| - | - |
| 이동윤| ldy8385ldy@gmail.com |

# 기타
#### Firebase 이용
#### Facebook api 이용

```

#### 현재 쓰인 라이브러리
// firebase
// FirebaseUI for Firebase Realtime Database
// 리사이클러뷰
// facebook auth api

