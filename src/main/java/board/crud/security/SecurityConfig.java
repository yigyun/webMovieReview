package board.crud.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    //  비밀번호를 Encoding하기 위해 추가한다. BCrypt 해싱 함수를 사용하는 방식이다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //  정적 자원에 대한 접근에 Spring security를 적용하지 않는다.
    @Bean
    WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.
                csrf().disable()
                        .headers().frameOptions().disable()

                //  Request에 대해 Path가 TWP 아래의 모든 접근을 허용한다는 뜻이다.
                .and()
                .authorizeRequests().requestMatchers(
                        new AntPathRequestMatcher(("/TPW/**"))).permitAll()
                //  h2 console 접근을 위해 해당 Path에 대한 csrf를 해제한다.
                .and()
                .csrf().ignoringRequestMatchers(
                        new AntPathRequestMatcher("/h2-console/**"))
                // XFrameOptionsHeaderWriter 클릭재킹을 대비하는 옵션이다. SAMEORIGIN이 특정 브라우저에 문제가 있을 수 있다고 한다.
                .and()
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                //  form 로그인에 대해 Spring security가 제공하는 페이지가 아닌 Page 설정, 로그인 성공시 이동할 Url을 설정한다.
                .and()
                .formLogin()
                .loginPage("/TPW/login").permitAll()
                .defaultSuccessUrl("/TPW/mainpage")
                //  logout 요청시 이동할 Path, 로그아웃을 성공하면 이동할 Url을 설정하고, 세션을 비워준다.
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/TPW/logout"))
                .logoutSuccessUrl("/TPW/login")
                .invalidateHttpSession(true)

                //.and()
                //.exceptionHandling().accessDeniedPage("/TPW/")

                .and().build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
