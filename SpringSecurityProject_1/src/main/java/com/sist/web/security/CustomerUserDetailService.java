package com.sist.web.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/*
 *  <input type="text" name="username">
 *  <input type="password" name="password">
 *  
 *   로그인 화면
 *       | - POST(/login) => username=admin, password=1234
 *  Spring Security
 *  	 |
 *  Authentication(인증)
 *       | - Success : 로그인 성공 => '/' => LoginSuccessHandler
 *       | - Failure : 로그인 실패(username or password 불일치) => '/login?error' => LoginFailHandler
 *         => formLogin : 로그인 처리 후 인증 => 해당 접속자의 정보를 읽는다
 *         				=> id, password, enable, roles
 *         				=> Principal: Session 로그인 정보, session 자체는 빈값
 *         				   => @GetMapping("/chat")
 *                            public String chat(HttpSession session, Principal p) {
 *                            	 UserVO vo=dao.infoData(p.username);
 *                               session.setAttribute(vo);
 *                            }
 *         => logout : Session 해제
 *       |
 *  DispatcherServlet
 *       |
 *  
 */
// MyBatis 연동
@Service
public class CustomerUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username.equals("admin")) {
			return User.builder()
					   .username("admin")
					   .password("{noop}1234") // {noop} : 암호화 없이 처리
					   .roles("ADMIN") // 권한
					   .build();
		}
		return User.builder()
				   .username("user")
				   .password("{noop}1234")
				   .roles("USER")
				   .build();
	}

}
