package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/*
 EMPNO                                     NOT NULL NUMBER(4)
 ENAME                                              VARCHAR2(10)
 JOB                                                VARCHAR2(9)
 MGR                                                NUMBER(4)
 HIREDATE                                           DATE
 SAL                                                NUMBER(7,2)
 COMM                                               NUMBER(7,2)
 DEPTNO                                             NUMBER(2)
 */
import java.util.*;
/*
 *  - JPQL
 *   1. DQL => SELECT => 메소드 규칙
 *   			ㄴfindByName(String name) : WHERE name=${name}
 *   2. DML => INSERT, UPDATE, DELETE
 *                |       |       |
 *                ---------    delete()
 *                    |
 *                  save()
 */
@Entity
@Table(name = "EMP")
@Getter
@Setter
public class Emp {
	@Id // PK
	private int empno;
	
	// int: null이 아닌 정수
	// Integer: null 허용 컬럼
	// null이 들어가는 데이터형은 클래스형(String,Integer)으로 선언
	
	//private Integer mgr,sal,comm,deptno;
	//private String ename,job;
	//private Date hiredate;
	private Integer mgr;
	private int sal;
	private Integer comm;
	private String ename;
	private String job;
	private Date hiredate;
	
	@ManyToOne
	@JoinColumn(name = "deptno")
	private Dept dept; // JOIN 걸리는 컬럼명 사용 불가
}
