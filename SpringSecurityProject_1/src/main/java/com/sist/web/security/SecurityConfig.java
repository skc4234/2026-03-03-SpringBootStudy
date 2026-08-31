package com.sist.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
/*
 *   
 *    Controller
 *       |
 *   SecurityConfig => URL별 권한 설정
 *       |
 *   CustomUserDetailsService => 사용자 정보 / 권한 정보
 *   ${session.id}
 *   
 *   .roles("ADMIN") => ROLE_ADMIN
 *   
 *   authority : 권한
 *   springmember : enable / userid / username / userpwd
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/*
		 *   접근 권한
		 *   로그인
		 *   로그아웃
		 *   자동로그인
		 */
		// 1. 인증 => 권한 부여
		/*
		 *   permitAll() : 모든 접속자 허용
		 *   authenticated() : 로그인 된 상태
		 *   hasRole("ADMIN") : ADMIN 만 접근
		 *   hasRole("USER") : USER만 접근
		 */
		http.csrf(csrf->csrf.disable())
		    .authorizeHttpRequests(auth->auth
		    		.requestMatchers("/","/login").permitAll()
		    		.requestMatchers("/admin").hasRole("ADMIN")
		    		.anyRequest().permitAll())
		    .formLogin(form->form
		    		.loginPage("/login")
		    		// Spring Security에서 /login을 POST 방식으로 넘길 시 처리
		    		// => Controller 처리가 아니라 Interceptor로 처리
		    		.loginProcessingUrl("/login")
		    		.defaultSuccessUrl("/",true)
		    		.failureUrl("/login?error")
		    		.permitAll())
		    .logout(logout->logout
		    		.logoutSuccessUrl("/"));
			// 자동 로그인
		return http.build();
	}
	// PasswordEncoding => 비밀번호 암호화
}
