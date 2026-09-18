package com.sist.web.vo;

import lombok.Data;

/*
CREATE TABLE learning_vector (
    id BIGSERIAL PRIMARY KEY,
    member_id BIGINT NOT NULL,
    exam_count INTEGER DEFAULT 0,
    average_score NUMERIC(5,2) DEFAULT 0,
    correct_count INTEGER DEFAULT 0,
    wrong_count INTEGER DEFAULT 0,
    accuracy_rate NUMERIC(5,2) DEFAULT 0,
    weak_theme VARCHAR(2000),
    learning_summary TEXT,
    content TEXT,
    embedding VECTOR(768),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_learning_vector_member UNIQUE (member_id)
);
 */
@Data
public class LearningVectorVO {
	private Long id,member_id;
	private int exam_count,correct_count,wrong_count;
	private double average_score,accuracy_rate;
	private String weak_theme,learning_summary,content,embedding;
}
