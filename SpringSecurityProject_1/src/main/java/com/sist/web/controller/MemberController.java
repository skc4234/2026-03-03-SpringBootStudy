package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	
	@GetMapping("/user")
	public String user() {
		return "user";
	}
}
