package com.sist.web.mapper.oracle;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.CourseVO;
import com.sist.web.vo.QuestionVO;

@Mapper
@Repository
public interface OracleEduMapper {
	/*
  <select id="courseAllData" resultType="com.sist.web.vo.CourseVO">
  	SELECT *
  	FROM course
  	ORDER BY no ASC
  </select>
  <select id="questionAllData" resultType="com.sist.web.vo.QuestionVO">
  	SELECT *
  	FROM exam_question
  	ORDER BY no ASC
  </select>
	 */
	public List<CourseVO> courseAllData();
	public List<QuestionVO> questionAllData();
}
