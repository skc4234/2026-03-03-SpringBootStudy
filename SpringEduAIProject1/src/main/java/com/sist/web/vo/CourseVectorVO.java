package com.sist.web.vo;

import lombok.Data;

/*
CREATE TABLE course_vector (
    id BIGSERIAL PRIMARY KEY,
    course_no BIGINT NOT NULL,
    title VARCHAR(1000) NOT NULL,
    content TEXT,
    instructor_no BIGINT,
    embedding VECTOR(768),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_course_vector_course UNIQUE (course_no)
);
create table course_vector(
	id BIGSERIAL primary key,
	course_no BIGINT not null,
	chunk_no INT not null,
	content TEXT not null,
	embedding VECTOR(768) not null,
	create_at TIMESTAMP default current_timestamp
);
 */
@Data
public class CourseVectorVO {
	private int id,course_no,instructor_no,chunk_no;
	private String content,embedding,title;
}
