package com.sist.web.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 사용자 조회 부분(데이터베이스)
@Service
public class CustomUserDetailService implements UserDetailsService {
	// 임시 => MyBatis => JDBC
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// {noop} => 암호화 없이 사용
		// Spring 5 부터는 비밀번호 반드시 암호화 => BCryptPasswordEncoder
		// => encode(): 암호화 / match(): 복호화
		// 같은 비밀번호가 있는 경우 => 패턴 여러개를 사용하기 때문에 암호화된 비밀번호는 서로 다르다
		if(username.equals("admin")) {
			return User.builder()
					.username("admin")
					.password("{noop}1234")
					.roles("ADMIN")
					.build();
		}
		return User.builder()
				.username("user")
				.password("{noop}1234")
				.roles("USER")
				.build();
	}

}
