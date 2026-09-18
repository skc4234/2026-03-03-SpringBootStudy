package com.sist.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
NO          NOT NULL NUMBER         
TITLE                VARCHAR2(4000) 
IMAGE                VARCHAR2(2000) 
ANSWER               VARCHAR2(2000) 
SCORE       NOT NULL NUMBER         
DESCRIPTION          CLOB           
THEME                NUMBER         
TYPE                 NUMBER         
DIFFICULTY           NUMBER(1)
 */
@Data
public class QuestionVO {
	private int no,score,theme,type,difficulty;
	private String title,image,answer,description;
	
	private ExamOptionVO evo=new ExamOptionVO();
}
