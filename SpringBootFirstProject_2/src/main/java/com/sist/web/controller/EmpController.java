package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
@Controller
@RequiredArgsConstructor
public class EmpController {
	private final EmpService service;
	
	@GetMapping("list")
	public String list(Model model) {
		List<EmpVO> list=service.empListData();
		model.addAttribute("list",list);
		return "list";
	}
}
