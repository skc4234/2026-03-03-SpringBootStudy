package com.sist.web.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/*
 NO                                        NOT NULL NUMBER
 NAME                                      NOT NULL VARCHAR2(51)
 SUBJECT                                   NOT NULL VARCHAR2(4000)
 CONTENT                                   NOT NULL CLOB
 PWD                                       NOT NULL VARCHAR2(20)
 REGDATE                                            DATE
 HIT                                                NUMBER
 
 JPA: Java Persistence API
 	- 자바 객체와 데이터베이스의 데이터를 연결해주는 ORM(Object Relation Mapper) 표준 기술
 	  -------  ---------
 	     |         |
 	     -----------
 	      ㄴ자동 SQL 문장 제작
 	- 기존
 	 	Java Object
 	 	     | -------- SQL 문장을 직접 만들어서 처리
 	 	오라클 테이블 연결
 	- JPA
 		Java Object
 		     | -------- JPA를 이용해서 자동 SQL 문장 생성
 		오라클 테이블 연결
 		
 	데이터베이스 테이블
 	------------------ Member
 	id    name   age
 	
 	==> @Entity
 		@Table(name="member") // className과 tableName이 다른 경우
 		@DynamicUpdate // 필요시 업데이트
 		public class MemberEntity {
 			@Id
 			private int id;
 			
 			private String name;
 			private int age;
 		}
 */

// Entity==Column => Entity에서 모든 설정
@Entity // 오라클 column과 완전한 매칭
@Table(name = "jpaboard") // 오라클 실제 테이블명
@DynamicUpdate // 필요시 업데이트 실시
@Data // getter,setter
@DynamicInsert
@SequenceGenerator(
	name = "jpb_no_seq",
	sequenceName = "jpb_no_seq",
	allocationSize = 1
)
// save(vo): 객체(Entity) === 오라클 column 연결
public class BoardEntity {
	@Id // 자동 증가 컬럼 => 자동으로 SQL 문장 만들어짐
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "jpb_no_seq")  // 시퀀스명
	private int no;
	
	// 비밀번호는 수정불가하게 설정
	@Column(insertable = true,updatable = false)
	private String pwd;
	
	@Column(insertable = true,updatable = false)
	@ColumnDefault("SYSDATE")
	private String regdate;
	
	private String name,subject,content;
	
	@ColumnDefault("0")
	private int hit;
	
	@PrePersist // 날짜 변환
	public void regdate() {
		this.regdate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
