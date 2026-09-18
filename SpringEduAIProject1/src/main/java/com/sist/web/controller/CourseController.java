package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import com.sist.web.vo.*;
import com.sist.web.service.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class CourseController {
	private final CourseService cService;
	private final QuestionService qService;
	
	@GetMapping("/course")
	public String course_vector() {
		/*
		 * List<CourseVO> list=cService.courseAllData();
		 * 
		 * for(CourseVO vo:list) { System.out.println(vo.getTitle()); }
		 * System.out.println(list.size()); return "course";
		 */
		cService.syncCourse();
		return "course";
	}
	
	@GetMapping("/question")
	public String question_page() {
		/*
		 * List<QuestionVO> list=cService.questionAllData();
		 * 
		 * for(QuestionVO vo:list) { System.out.println(vo.getTitle()); }
		 * System.out.println(list.size());
		 */
		qService.syncQuestion();
		return "question";
	}
}
