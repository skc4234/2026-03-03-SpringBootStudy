package com.sist.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;
@Controller
public class EmpController {
	@Autowired
	private EmpService service;
	
	@GetMapping("/emp/list")
	public String emp_list(Model model) {
		// model => 데이터 전송 객체 => request 대신 사용
		List<EmpVO> list=service.empListData();
		model.addAttribute("list",list);
		return "/emp/list";
	}
}
