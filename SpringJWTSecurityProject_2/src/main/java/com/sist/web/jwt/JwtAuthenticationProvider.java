package com.sist.web.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;
import com.sist.web.service.CustomUserDetailsService;
/*
 *   1. 사용자 정보 저장 => UserDetailsService
 *   2. 토큰 생성 => Provider
 *   3. 통합 => filter
 *   4. 권한 => URI 접근 => Config
 *   5. 실제 사용자로부터 요청 => controller
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtAuthenticationProvider {
	private final String SECRET="my-secret-key-my-security-key-my-secret-key";
	public String createToken(String username,String role) {
		/*
		 *   Payload {
		 *   	sub: "admin",
		 *   	role: "ROLE_ADMIN"
		 *   } ==> token 형식(bytes)
		 */
		return Jwts.builder()
				   .setSubject(username) // 사용자 ID 저장 {sub: admin}
				   .claim("role", role) // 권한 추가 => ROLE_ADMIN
				   .setIssuedAt(new Date()) // JWT 발급 시간 저장
				   // 만료시간 등록
				   // 3600000 => 1시간(60분) => 60*60*1000
				   .setExpiration(new Date(System.currentTimeMillis()+3600000))
				   // SECRET key를 이용해서 JWT에 서명
				   .signWith(Keys.hmacShaKeyFor(SECRET.getBytes())) 
				   .compact();
	}
	
	public String getUsername(String token) {
		
		return Jwts.parserBuilder()
				   .setSigningKey(SECRET.getBytes())
				   .build()
				   .parseClaimsJws(token)
				   .getBody()
				   .getSubject();
	}
	public boolean validate(String token) {
		try {
			Jwts.parserBuilder()
			    .setSigningKey(SECRET.getBytes())
			    .build()
			    .parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
