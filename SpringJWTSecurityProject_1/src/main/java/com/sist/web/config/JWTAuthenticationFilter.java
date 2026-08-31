package com.sist.web.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
 *  - JWT
 *    1. 동작 순서
 *    2. JWT 개념
 *    3. Spring Security 동작
 *    4. 각 클래스의 역할
 *       - AuthenticationFilter
 *       - AuthenticationProvider
 *       - SecurityConfig
 *    5. Controller ==> ThymeLeaf
 *    -------------------------------
 *    1. 동작 순서
 *       ```
 *       로그인 요청
 *           | ---- POST: /member/login
 *     AuthController
 *           | ---- AuthenticationManager.authenticate()
 *           | ---- 인증 여부 확인
 *     CustomUserDetailsService
 *           | ==== 사용자 검색
 *      UserDetails
 *           | ---- 저장 => 인증 성공
 *     Authentication 성공
 *           |
 *    AuthenticationProvider
 *           | ---- createToken()
 *       JWT 발급 => 기간 설정(쿠키에 저장)
 *           |
 *        브라우저
 *           |
 *    AuthenticationFilter
 *           | ---- Authentication 헤더 확인
 *           | ---- 토큰 추출
 *           | ---- JWT 검증
 *           | ---- username 추출
 *           | ---- UserDetails 에서 조회
 *           | ---- SecurityContext 정보 저장
 *      Controller에 접근
 *   -------------------------------------
 *    2. JWT 개념
 *       - JSON Web Token
 *       - ex) xxxxx.yyyyy.zzzzz
 *             Header.Payload.Signature
 *             Payload : 실제 정보 저장
 *   ------------------------------------
 *    3. Spring Security 동작과 차이
 *     1) Spring Security
 *        로그인
 *          |
 *      ID / PW 확인
 *          |
 *      Session 생성
 *          |
 *     JSESSIONID 쿠키 저장
 *          |
 *       다음 요청
 *          |
 *      Session 확인
 *          |
 *     로그인 사용자 확인
 *       => 서버가 로그인 상태를 가지고 있다.
 *       => Session 기반
 *       
 *    2) JWT
 *        로그인
 *          |
 *       ID / PW 확인
 *          |
 *       JWT 생성
 *          |
 *     클라이언트가 JWT 저장
 *          |
 *       다음 요청
 *          |
 *    Authorization: Bearer JWT
 *          |
 *      서버가 JWT 검증
 *          |
 *      로그인 사용자 확인
 *        => 세션없이 쿠키만 사용
 *       
 *     
 */
// 사용자 요청시마다 JWT 토큰 검사 => OncePerRequestFilter : 요청당 한번만 실행됨
public class JWTAuthenticationFilter extends OncePerRequestFilter {
	// 사용자 정보를 담고 있음 => 사용자 정보를 데이터베이스를 연동해 데이터 추출
	private final UserDetailsService userDetailsService;
	// JWT 토큰을 만들어서 검증하는 역할 수행
	private final JWTTokenProvider provider;
	
	public JWTAuthenticationFilter(UserDetailsService userDetailsService, JWTTokenProvider provider) {
		this.userDetailsService=userDetailsService;
		this.provider=provider;
	}
	
	// 실제 요청시마다 처리하는 메소드
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// JWT 토큰을 저장할 변수
		String token = null;

		// 헤더 생성 => Authorization: Bearer asdfewionvafdsg
		// 1. Authorization Header 확인
		String header = request.getHeader("Authorization");
        System.out.println("header="+header);
		if (header != null && header.startsWith("Bearer ")) {

		    token = header.substring(7);
		}

		// 2. Header가 없으면 Cookie 확인
		if (token == null && request.getCookies() != null) {

		    for (Cookie cookie : request.getCookies()) {

		        if ("accessToken".equals(cookie.getName())) {

		            token = cookie.getValue();
		            System.out.println("token="+token);
		            break;
		        }
		    }
		}

		// 3. JWT가 존재하면 인증
		// => 1. 유효기간 설정
		if (token != null && provider.validate(token)) {

		    String username =
		            provider.getUsername(token);

		    UserDetails user =
		            userDetailsService
		                    .loadUserByUsername(username);

		    UsernamePasswordAuthenticationToken auth =
		            new UsernamePasswordAuthenticationToken(
		                    username,
		                    null,
		                    user.getAuthorities()
		            );

		    SecurityContextHolder
		            .getContext()
		            .setAuthentication(auth);
		}
		// 정상적으로 컨트롤러가 다음을 수행할 수 있게 처리
		filterChain.doFilter(request, response);
	}

}
