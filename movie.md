# 영화 관련 정보

## Movie.java
이 파일은 Movie 엔티티로, 기본적인 영화 관련 정보 필드와 @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY) boardList로 영화와 연관된 게시물의 목록도 있다.

## MainController.java
이 파일은 메인 화면, 영화 리스트, 영화 상세 정보 페이지에 대한 컨트롤러 이다. Login이 필요한 곳에 @Login 어노테이션을 통해 인증을 요구한다.

## BoardController.java
이 파일은 게시물 관련 컨트롤러로 영화에 대한 게시물 관련 CRUD에 대한 컨트롤러이다. Login이 필요한 곳에 @Login 어노테이션을 통해 인증을 요구한다.

## MovieApiService.java
Repository에 저장된 정보를 호출하는 서비스 역할을 담당한다.

## PreRegisterContents.java
서버 시작시에 "https://api.themoviedb.org/3/trending/movie/week?key정보"를 통해 원하는 영화 정보를 가져오고, 해당 데이터를 파싱하여 원하는 형태로 바꾸어 Movie 객체로 저장한다.
