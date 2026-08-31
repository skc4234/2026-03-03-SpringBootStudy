package com.sist.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.jwt.JwtAuthenticationProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final JwtAuthenticationProvider provider;
	private final AuthenticationManager manager;
	
	@RequestMapping("/member/login")
	// 와일드 카드 (?) => 리턴형 Object
	public ResponseEntity<?> login(
			@RequestParam(value = "username",required = false) String username,
			@RequestParam(value = "password",required = false) String password){
		System.out.println("username: "+username);
		// 1. ID / PW 인증
		Authentication auth=
				manager.authenticate(
					new UsernamePasswordAuthenticationToken(username,password)
				);
		
		// 2. 인증된 사용자 정보 가져오기
		UserDetails user=(UserDetails)auth.getPrincipal();
		
		// 3. 권한 부여
		String role=user.getAuthorities().iterator().next().getAuthority();
		System.out.println("role: "+role);
		
		
		// 4. JWT 생성
		String token=provider.createToken(user.getUsername(), role);
		
		// 5. Cookie 생성
		ResponseCookie cookie=
				ResponseCookie.from("accessToken",token)
				              .httpOnly(true)
				              .secure(false)
				              .path("/")
				              .maxAge(3600)
				              .build();
		System.out.println("cookie: "+cookie);
		
		// 6. home으로 이동
		return ResponseEntity.status(HttpStatus.FOUND)
				             .header(HttpHeaders.SET_COOKIE,cookie.toString())
				             .header(HttpHeaders.LOCATION,"/home")
				             .build();
	}
}
