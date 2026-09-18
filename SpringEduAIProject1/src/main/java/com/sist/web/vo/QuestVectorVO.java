package com.sist.web.vo;

import lombok.Data;

/*
CREATE TABLE question_vector (
    id BIGSERIAL PRIMARY KEY,
    question_no BIGINT NOT NULL,
    title VARCHAR(4000) NOT NULL,
    description TEXT,
    theme INTEGER,
    type INTEGER,
    difficulty INTEGER NOT NULL,
    answer VARCHAR(2000) NOT NULL,
    option1 VARCHAR(2000),
    option2 VARCHAR(2000),
    option3 VARCHAR(2000),
    option4 VARCHAR(2000),
    content TEXT,
    embedding VECTOR(768),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_question_vector_question UNIQUE (question_no)
);
 */
@Data
public class QuestVectorVO {
	private Long id,question_no;
	private int theme,type,difficulty;
	private String title,description,answer,option1,option2,option3,option4,
		content,embedding;
}
