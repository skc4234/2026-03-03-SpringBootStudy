package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DEPT")
@Getter
@Setter
public class Dept {
	@Id
	private int deptno;
	
	private String dname;
	private String loc;
}
