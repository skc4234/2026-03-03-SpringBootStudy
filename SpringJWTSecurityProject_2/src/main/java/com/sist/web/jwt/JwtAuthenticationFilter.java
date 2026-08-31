package com.sist.web.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sist.web.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final CustomUserDetailsService userDetailsService;
	private final JwtAuthenticationProvider provider;
	
	public JwtAuthenticationFilter(CustomUserDetailsService userDetailsService,
			JwtAuthenticationProvider provider) {
		this.userDetailsService=userDetailsService;
		this.provider=provider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 토큰 생성
		String token=null;
		// 1. Header: 프론트에서 전송 {Authorization: Bearer adsfqawvadgag}
		String header=request.getHeader("Authorization");
		
		if(header!=null && header.startsWith("Bearer ")) {
			token=header.substring(7);
		}
		
		// Cookie 확인
		else if(token==null && request.getCookies()!=null) {
			for(Cookie cookie:request.getCookies()) {
				if("accessToken".equals(cookie.getName())) {
					token=cookie.getValue();
					break;
				}
			}
		}
		
		// 3. JWT 검증
		if(token!=null && provider.validate(token)) {
			String username=provider.getUsername(token);
			UserDetails user=
					userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth=
					new UsernamePasswordAuthenticationToken(
							user,
							null, // 인증 정보 => credentials : 자격 정보
							user.getAuthorities()
			        );
			// 인증 정보 저장
			SecurityContextHolder.getContext()
			                     .setAuthentication(auth);
		}
		
		// 4. DB에서 사용자 조회
		// 5. Security 인증 객체 생성
		// 6. Security Context 저장
		
		// 7. 다음 Filter / Controller 실행
		filterChain.doFilter(request, response);
	}
}
