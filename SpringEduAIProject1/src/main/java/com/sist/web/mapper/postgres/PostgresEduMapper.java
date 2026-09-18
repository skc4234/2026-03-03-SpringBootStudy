package com.sist.web.mapper.postgres;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.CourseVectorVO;
import com.sist.web.vo.QuestVectorVO;
import com.sist.web.vo.QuestionVO;

@Mapper
@Repository
public interface PostgresEduMapper {
	public void saveCourseVector(CourseVectorVO vo);
	public void saveQuestionVector(QuestVectorVO vo);
}
