# webMovieReview
스프링 부트, 타임리프를 기반으로 한 사이트

## 🖥️ 프로젝트 소개
MEGA BOX를 참고하여 만든 영화 예매 사이트입니다.
<br>

## 개발 기간
- 23.02.26일 - 23.03.16일

### ⚙️ 개발 환경
- Java 11
- Framework : Springboot(2.x)
- Database : PostgreSQL, Test-H2
- IDE : IntelliJ
- Other : Spring Data JPA, Json-Simple, lombok, Spring MVC

## 📌 주요 기능
#### 로그인
- 로그인 시 Username, Password 검증
- 로그인 시 쿠키(Cookie) 및 세션(Session) 생성
#### 회원가입
- 닉네임, ID 중복 확인
- Password, ID의 패턴 및 길이 확인
#### 영화 서비스
- tmdb API를 통해 영화 관련 데이터 연동
- 영화 Detail(주요 줄거리) 페이지
- 리뷰 게시판(각 영화의 리뷰 리스트를 가져온다) 관련 CRUD 기능

## 리팩토링
- Exception 핸들링, 처리 with Spring AOP
- Junit 테스트 작성
