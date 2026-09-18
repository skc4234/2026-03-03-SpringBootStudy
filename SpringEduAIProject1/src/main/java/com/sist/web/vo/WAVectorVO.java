package com.sist.web.vo;
/*
CREATE TABLE wrong_answer_vector (
    id BIGSERIAL PRIMARY KEY,
    enrollment_no BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    exam_no BIGINT NOT NULL,
    question_no BIGINT NOT NULL,
    exam_title VARCHAR(1000),
    user_answer VARCHAR(2000),
    correct_answer VARCHAR(2000),
    question_title VARCHAR(4000),
    description TEXT,
    theme INTEGER,
    type INTEGER,
    difficulty INTEGER,
    content TEXT,
    embedding VECTOR(768),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
 */
// 오답 처리 => 오답노트

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WAVectorVO {
	private Long id,enrollment_no,member_id,exam_no,question_no;
	private int theme,type,difficulty;
	private String exam_title,user_answer,correct_answer,question_title,
		description,content,embedding;
	private LocalDateTime created_at;
}
