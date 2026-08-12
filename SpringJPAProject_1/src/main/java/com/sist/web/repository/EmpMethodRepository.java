package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sist.web.entity.Emp;
import java.util.*;

public interface EmpMethodRepository extends JpaRepository<Emp, Integer> {
	// findBy => WHERE
	// 1. 상세보기 => empno 검색
	// WHERE empno=?
	public Emp findByEmpno(int empno);
	
	// 2. 이름 검색(중복)
	List<Emp> findByEname(String ename);
	// WHERE ename=?
	
	// 3. LIKE => A% / %A / %A%
	// startsWith / endsWith / Contains
	// WHERE ename LIKE '?%'
	List<Emp> findByEnameStartsWith(String ename);
	// WHERE ename LIKE '%?'
	List<Emp> findByEnameEndsWith(String ename);
	// WHERE ename LIKE '%?%'
	List<Emp> findByEnameContains(String ename);
	
	// 4. 비교: <=, >=, >, <
	// WHERE sal >= ?
	List<Emp> findBySalGreaterThanEqual(int sal);
	// WHERE sal <= ?
	List<Emp> findBySalLessThanEqual(int sal);
	// WHERE sal BETWEEN ? AND ?
	List<Emp> findBySalBetween(int min,int max);
	// AND
	// WHERE JOB=? AND SAL>?
	List<Emp> findByJobAndSalGreaterThan(String job,int sal);
	// OR
	// WHERE job=? OR ename=?
	List<Emp> findByJobOrEname(String job,String ename);
	
	// JOIN
	// 부서명(dept 테이블 조인)으로 emp 검색
	List<Emp> findByDeptDname(String dname);
	List<Emp> findByDeptLoc(String loc);
	
	// 부서명 Like
	List<Emp> findByDeptDnameContains(String dname);
	List<Emp> findByDeptLocContains(String loc);
	
	// 정렬
	// ORDER BY sal DESC
	List<Emp> findByOrderBySalDesc();
	// Top-N : 정렬 후 상위 n개 row 반환
	// WHERE rownum<=3 ORDER BY sal DESC
	List<Emp> findTop3ByOrderBySalDesc();
	
	// 중복 제거
	// WHERE DISTINCT job=?
	List<Emp> findDistinctByJob(String job);
	
	// IS NOT NULL / IS NULL
	// WHERE comm IS NULL
	List<Emp> findByCommIsNull();
	// WHERE comm IS NOT NULL
	List<Emp> findByCommIsNotNull();
	
	// IN
	// WHERE deptno IN(?,?,?)
	// List<Integer> deptnos = List.of(10,20,30)
	List<Emp> findByDeptDeptnoIn(List<Integer> deptnos);
	
	// NOT
	// WHERE job<>?
	List<Emp> findByJobNot(String job);
}
