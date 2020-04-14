package com.sonjg.book.springboot.config.auth;

import com.sonjg.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-option 사용 위해서 비활성화

            .and()
                .authorizeRequests() // url별 권환 관리 설정 옵션 .antMatchers 사용 가능하게 함
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // 모두에게 주어진 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 일반 사용자에게만 주어진 권한
                .anyRequest().authenticated() // 이외 url - authenticated: 인증된, 로그인 한 사용자들에게만 허용
            .and()
                .logout()
                    .logoutSuccessUrl("/") // logout 시 redirect 되는 경로
            .and()
                .oauth2Login()
                    .userInfoEndpoint() //OAuth2 login 성공 시 사용자 정보 취득 설정
                        .userService(customOAuth2UserService); // userService 인터페이스 구현체 등록
    }
}
