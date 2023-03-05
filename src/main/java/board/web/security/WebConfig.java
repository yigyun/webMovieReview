package board.web.security;

import board.web.dto.LoginMemberArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/TPW/login", "/TPW/logout", "/TPW/register/new",
                        "/TPW/css/**", "/TPW/*.ico", "/TPW/error", "/TPW/js/**"
                        ,"/css/**", "/js/**", "/image/**"
                );
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver>
                                             resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }
}
