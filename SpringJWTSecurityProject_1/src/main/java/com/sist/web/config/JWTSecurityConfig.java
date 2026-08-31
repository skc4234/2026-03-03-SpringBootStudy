package com.sist.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JWTSecurityConfig {
	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter(
			UserDetailsService userDetailsService, JWTTokenProvider provider) {
		return new JWTAuthenticationFilter(userDetailsService, provider);
	}
	
	@Bean
	public SecurityFilterChain filterChain(
			HttpSecurity http, JWTAuthenticationFilter filter)
			throws Exception {
		http.csrf(csrf->csrf.disable())
		    .sessionManagement(session->session.sessionCreationPolicy(
		    	SessionCreationPolicy.STATELESS // Ssssion 유지 안함
		    ))
		    .authorizeHttpRequests(auth->auth
		    		.requestMatchers("/","/login").permitAll()
		    		.requestMatchers("/admin").hasRole("ADMIN")
		    		.anyRequest().permitAll())
		    .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
}
