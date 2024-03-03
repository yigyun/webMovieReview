# Member 관련 정보

Member는 @EntityListeners(AuditingEntityListener.class)로 date를 추적할 수 있게 하고, @NoArgsConstructor(access = AccessLevel.PROTECTED)로 아무 의미없는 값을 갖는 것을 방지한다. @Builder를 통해 유연한 생성을 한다.

## MemberFormDTO.java
이 파일은 NotBlank, NotEmpty, @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,14}"를 통해 패스워드, 이름 등의 validation을 추가했다.

## RegistryController.java
이 파일에서 회원 가입을 처리하는 컨트롤러 클래스입니다. create 메소드에서는 MemberFormDTO 객체를 받아 MemberDTO 객체로 변환하고, 이를 MemberService를 통해 데이터베이스에 저장합니다.

## MemberService.java
이 파일은 회원 관련 비즈니스 로직을 처리하는 서비스 클래스입니다. 이 클래스는 MemberRepository를 주입받아 사용합니다. join으로 Member 등록을 하는데 한 번더 Repository에서 중복을 확인하고, save를 진행합니다.
