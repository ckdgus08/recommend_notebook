# 전공에 따른 노트북 추천 사이트

deep learning 서버 실행

```python
conda activate deep
python manage.py runserver
```

### 프로젝트 개요 :

대부분의 대학생들이 노트북을 구매한다. 하지만 몇몇 노트북에 관심이 많은 학생을 제외하곤 노트북을 구매하는데 어려움을 겪는다. 특히 신입생의 경우에는 전공에서 어떤 프로그램을 사용하는지 조차 알기 힘들기에 더더욱
적합한 노트북을 선택하는데 어려움이 있다.

### 프로젝트 목적 :

인터넷 상에 존재하는 다양한 정보들을 활용하여 전공에 적합한 노트북을 추천하는 것이 목표이다.

### 기술스택

* 언어 : java, js, python
* 프레임워크 : Spring boot, Django, Pytorch
* 데이터베이스 : mysql or neo4j
* ORM : JPA, query dsl
* Build tool : Gradle

* OS : Mac OS
* IDE : Intellij, VScode
* 서버 : aws
* CI/CD : docker + jenkins

### 전공별 노트북 사양 데이터 수집을 위한 설문조사

* [설문조사 링크](https://docs.google.com/forms/d/1zzALaBEOnt_9-ysxn4kaH6MlEbHc2pRQ6lBR6gJ_B_o)

참고사이트 :  [애플m1 호환 가능여부 확인하는 사이트](https://isapplesiliconready.com/)

### 요구사항, 기능

1. 분류 기준 : [ 전공 / 사용하는 프로그램 / 밝기 / 가격 / 무게 / 데스크탑 보유 / 성능점수 / 같은 전공별 후기 / 발열 / 배터리용량 / 터치가능여부 / 지문 / pd충전 / ]
1. 부품에 대한 친절한 설명 / 제품번호 정하는 방식 설명
1. 다른 노트북과 비교할때 어떤 부품이 다른지 명확히 표시
1. 전공자가 사용했던 노트북 중고거래 가능하도록 공간 마련
1. 프로그램 검색시 잘 돌아가는지 체크하는 기능
1. 같은 전공인 사람이 등록한 후기 수 / 후기 쓰는 공간 / 후기 신뢰도
1. 학교별 프로그램 라이센스 지원여부 / 대학생이 받을 수 있는 혜택