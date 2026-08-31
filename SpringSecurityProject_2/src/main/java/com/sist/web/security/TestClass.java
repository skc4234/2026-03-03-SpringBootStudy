package com.sist.web.security;
/*
 *   - 인증 처리
 *   	1. 사용자가 로그인 요청
 *   		=> username(ID), password(PWD)
 *   		=> formLogin
 *   	2. UsernamePasswordAuthenticationFilter
 *   		=> 요청 인터셉트
 *   	3. UsernamePasswordAuthenticationToken
 *   	    => 인증용 객체 생성
 *      4. 토큰 => AuthenticationManager 에 전송
 *                         |
 *                AuthenticationProvider
 *                         |
 *                 UserDetailsService =>  DB에서 사용자 정보 조회
 *                 =>  조회된 데이터(사용자 정보) => id, password 확인
 *                   ----------> UserDetails
 *      5. 인증 성공 시 SecurityContext에 저장
 *      
 *      
 *      - UserDetails
 *      - UserDetailsService
 *      - PasswordEncoder
 */
public class TestClass {

}
