package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.oracle.OracleEduMapper;
import com.sist.web.mapper.postgres.PostgresEduMapper;
import com.sist.web.vo.QuestVectorVO;
import com.sist.web.vo.QuestionVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	private final OracleEduMapper oMapper;
	private final PostgresEduMapper pMapper;
	private final EmbeddingService eService;
	
	public void saveQuestionVector(QuestVectorVO vo) {
		pMapper.saveQuestionVector(vo);
	}
	
	public void syncQuestion() {
		List<QuestionVO> questions=oMapper.questionAllData();
		for(QuestionVO question:questions) {
			String content=createQuestionContent(question);
			float[] embedding=eService.createEmbedding(content);
			String eString=eService.toVectorString(embedding);
			QuestVectorVO vo=new QuestVectorVO();
			vo.setQuestion_no((long)question.getNo());
			vo.setTheme(question.getTheme());
			vo.setType(question.getType());
			vo.setDifficulty(question.getDifficulty());
			vo.setTitle(question.getTitle());
			vo.setDescription(question.getDescription());
			vo.setAnswer(question.getAnswer());
			vo.setOption1(question.getEvo().getOption1());
			vo.setOption2(question.getEvo().getOption2());
			vo.setOption3(question.getEvo().getOption3());
			vo.setOption4(question.getEvo().getOption4());
			vo.setContent(content);
			vo.setEmbedding(eString);
			saveQuestionVector(vo);
		}
		
	}
	
	public String createQuestionContent(QuestionVO vo) {
		return """
				문제: %s
				선택지 1: %s
				선택지 2: %s
				선택지 3: %s
				선택지 4: %s
				정답: %s
				해설: %s
				문제 유형: %s
				난이도: %s
				""".formatted(
					vo.getTitle(),
					vo.getEvo().getOption1(),
					vo.getEvo().getOption2(),
					vo.getEvo().getOption3(),
					vo.getEvo().getOption4(),
					vo.getAnswer(),
					vo.getDescription(),
					vo.getTheme(),
					vo.getDifficulty()
				);
	}
	
}
