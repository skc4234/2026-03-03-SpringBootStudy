package com.sist.web.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.util.*;
import com.sist.web.vo.*;
import com.sist.web.mapper.*;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
	private final UserMapper mapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		MemberVO user=mapper.findByUserid(username);
		if(user==null) {
			throw new UsernameNotFoundException("UserName을 찾을 수 없습니다...");
		}
		List<String> roles=mapper.findRolesByUserid(username);
		
		// 권한 저장
		// 중복 없이 저장
		Set<GrantedAuthority> authorities=new HashSet<GrantedAuthority>();
		for(String role:roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		
		System.out.println("ID: "+user.getUsername());
		System.out.println("PW: "+user.getUserpwd());
		
		return new User(user.getUsername(),user.getUserpwd(),user.getEnable()==0?false:true,true,true,true,authorities);
		// 1. id 저장  => username
		// 2. pwd 저장 => userpwd
		// 3. enable 저장 => enable => 계정 활성화 여부
		// 4. true : 계정 만료 여부
		// 5. true : 계정 잠금 여부
		// 6  true : 비밀번호 만료 여부
		// 7. authorities : 권한 정보
		// ===> Principal 객체에 저장
		
	}

}
