package com.sist.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;
import com.sist.web.service.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomerUserDetailsService userDetailsService;
	
	// 재정의 : 권한에 따라 접근 여부 확인 => 로그인 / 로그아웃 / 자동로그인 / 소셜로그인(Kakao,Naver,Google)
	
	// --- SecurityFilterChain ---
	// 1. URL 마다 권한 부여
	/*
	 *   1) CSRF(Cross Site Request Forgery): 공격자가 인증된 브라우저에서 저장된 쿠키나 세션을 활용해서 request 요청
	 *   	=> csrf->csrf.disable()
	 *   2) authorizeHttpRequests: 인증, 인가에 필요한 URL 지정
	 *  	- anyRequest() / requestMatchers : 지정된 URL에 권한 부여
	 *      - permitAll() / denyAll() / hasRole("") / hasAnyRoles("","","")
	 *   	   => 접근 거부시 403 error
	 *   	- authenticated(): 인증된 유저(로그인된)
	 */
	// 2. formLogin
	// 3. logout
	// 4. remember-me
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(auth->auth
					.requestMatchers("/","/join","/login").permitAll()
					.requestMatchers("/user**").authenticated()
					.requestMatchers("/admin**").hasRole("ADMIN")
					.anyRequest().permitAll()) // 게스트 포함
			.formLogin(form->form
					.loginPage("/login")
					.loginProcessingUrl("/login_process")
					.defaultSuccessUrl("/",true)
					.failureHandler(loginFailHandler()))
			.logout(logout->logout
					.logoutSuccessUrl("/")); 
		return http.build();
	}
	
	// 5. 비밀번호 암호화(필수)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 6. 로그인 성공 / 실패
	@Bean
	public AuthenticationFailureHandler loginFailHandler() {
		return new LoginFailHandler();
	}
}
