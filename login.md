# 로그인 관련 정보

로그인 모듈은 사용자 인증 및 사용자 세션 관리를 담당합니다. 이 모듈은 LoginService, LoginCheckInterceptor, SessionInfoController, SessionManager, 그리고 MemberController 등의 주요 컴포넌트로 구성되어 있습니다.

### Flow
사용자가 로그인을 시도하면 LoginService가 사용자 인증을 수행합니다. 인증이 성공하면 SessionManager가 사용자에 대한 세션을 생성합니다. 이후 모든 사용자 요청에 대해 LoginCheckInterceptor가 사용자가 로그인되어 있는지 확인하고, 그렇지 않으면 사용자를 로그인 페이지로 리다이렉트합니다. 사용자는 SessionInfoController를 통해 세션 정보를 확인할 수 있습니다.

## LoginService.java
이 파일은 사용자 로그인 기능을 담당합니다. 사용자로부터 입력받은 `username`과 `password`를 이용하여 로그인을 시도합니다. `MemberRepository`를 통해 데이터베이스에서 해당 `username`을 가진 사용자를 찾고, 찾아낸 사용자의 `password`가 입력받은 `password`와 일치하는지 확인합니다. 일치하면 해당 사용자 객체를 반환하고, 일치하지 않으면 `null`을 반환합니다.

## LoginCheckInterceptor.java
이 파일은 인터셉터를 구현한 것으로, 사용자의 요청이 들어올 때마다 실행됩니다. 이 인터셉터는 사용자가 로그인 상태인지 확인하고, 로그인 상태가 아니라면 로그인 페이지로 리다이렉트합니다.

## SessionInfoController.java
이 파일은 세션 정보를 확인하는 컨트롤러입니다. `/TPW/session-info` 경로로 요청이 들어오면, 현재 세션의 정보를 로그로 출력하고 "세션 출력"이라는 문자열을 반환합니다.

## SessionManager.java
이 파일은 세션을 관리하는 클래스입니다. 세션 생성, 조회, 만료 기능을 제공합니다. 세션 생성 시에는 `UUID`를 이용하여 세션 ID를 생성하고, 이를 쿠키로 사용자에게 전달합니다. 세션 조회 시에는 요청으로부터 받은 쿠키를 이용하여 세션을 찾습니다. 세션 만료 시에는 요청으로부터 받은 쿠키를 이용하여 세션을 삭제합니다.

## WebConfig.java
Spring MVC의 설정을 담당하는 클래스입니다. 이 클래스는 `WebMvcConfigurer` 인터페이스를 구현하고 있습니다. `WebMvcConfigurer` 인터페이스를 구현하는 이유는 Spring MVC의 기본 설정을 커스터마이징하기 위해서입니다. `addInterceptors` 메소드를 통해 인터셉터를 등록해서 컨트롤러 요청 처리 전후에 모든 요청에 대해서 특정 경로를 예외로 두어 인증, 인가를 진행하여 리소스에 대한 접근, 유저 인증을 확인한다.

## MemberController
MemberController는 로그인 및 로그아웃 요청을 처리합니다. LoginService를 사용하여 사용자를 인증하고 SessionManager를 사용하여 사용자 세션을 관리합니다.

### 보안
로그인 모듈은 사용자 세션을 유지하기 위해 세션 쿠키를 사용합니다. 세션 ID는 UUID를 사용하여 생성되며, 이는 높은 수준의 무작위성과 고유성을 제공합니다. 세션 정보는 서버 측에 저장되므로 클라이언트 측에서 민감한 정보가 노출되는 것을 방지합니다.
