package com.sist.web.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.web.vo.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final MemberService mService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// 1. Member 테이블 조회
		MemberVO member=mService.findByUserId(username);
		
		// 2. 사용자가 없는 경우
		if(member==null) {
			// 예외처리 발생
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: "+username);
		}
		
		// 3. 휴먼 계정인 경우
		if(member.getEnable()!=1) {
			throw new UsernameNotFoundException("휴먼 계정입니다: "+username);
		}
		
		// 4. Authority 권한 읽기
		List<AuthorityVO> authorityList=mService.getAutorityData(username);
		
		// 5. DB 권한 => Spring Security로 변환
		List<SimpleGrantedAuthority> authorities=
				authorityList.stream()
				             .map(a->new SimpleGrantedAuthority(a.getAuthority()))
				             .toList();
				 
		// 6. UserDetails에 저장
		return User.builder()
				   .username(member.getUserid())
				   .password(member.getUserpwd())
				   .authorities(authorities)
				   .build();
	}
}
