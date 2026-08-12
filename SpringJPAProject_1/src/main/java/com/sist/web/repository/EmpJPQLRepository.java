package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;

/*
 *    JPA
 *    1. Query Method: findBy...
 *    	- 자동으로 SQL 문장 제작 => JPQL 형식
 *    	- 장점
 *    		- SQL 문장 자동 생성 => SQL 몰라도 사용 가능
 *    		- 메소드명으로 SQL 문장 식별 가능 => 가독성이 좋다
 *    	- 단점
 *    		- 메소드명이 길어질 수 있다 
 *    		=> findBySalGreaterThanAndEnameContainsAndJobStartsWithOrderByHiredateDesc
 *    	- 간단한 문장에서만 사용(목록 출력, 단순 검색, 상세보기 등)
 *    	- 동적 쿼리 사용 불가 ==> MyBatis 사용
 *    	- 사용빈도 MyBatis 8:2 JPA 
 *    
 *    2. JPQL: @Query(`SELECT ...`)
 *    	- JPA에서 제공하는 객체 중심의 SQL 문장
 *    	- 장점
 *    		- JPA 표준 기술 => 라이브러리가 필요없다 => 바로 사용 가능
 *   		- 객체 지향 : 테이블이 아닌 Entity 객체 사용
 *  	- 단점
 *  		- 문자열 기반 => 오타 시 에러 잡기 어렵다
 *  		- 동적 쿼리 사용이 어렵다
 *      - 복잡하지 않은 SQL 문장 제작 시 사용(수정/삭제 등)
 *      
 *    3. QueryDSL
 *    	- 타입의 안정성 => 데이터형 제한이 없다
 *    	- 단점
 *    		- 사용을 위한 초기 설정이 어렵다
 *    			=> Q-class 생성 필요
 *    		- 문법이 어렵다
 *    	- 복잡한 JOIN, 필터링 등에서 사용된다
 *    --------------------------------------------
 *    - 단순 조회 : Query Method
 *    - 복잡한 검색 / JOIN 등 : QueryDSL / MyBatis
 *    - 수정, 삭제, 정적쿼리 : JPQL
 */
import java.util.*;

@Repository
public interface EmpJPQLRepository extends JpaRepository<Emp, Integer>{
	@Query("SELECT e FROM Emp e") // Emp는 테이블명이 아닌 Entity 객체명
	// 반드시 별칭 사용
	public List<Emp> empListData();
	
	// public Emp findByEmpno(int empno);
	@Query("SELECT e FROM Emp e WHERE e.empno=:empno")
	Emp empDetailData(@Param("empno") int empno);
	
	// List<Emp> findByEname(String ename);
	@Query("SELECT e FROM Emp e WHERE e.ename=:ename")
	List<Emp> empEnameFind(@Param("ename") String ename);
	
	// List<Emp> findByEnameStartsWith(String ename);
	// :ename||'%' === CONCAT(:ename,'%')
	@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT(:ename,'%')")
	List<Emp> empEnameStartsLike(@Param("ename") String ename);
	
	// List<Emp> findByEnameEndsWith(String ename);
	@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%',:ename)")
	List<Emp> empEnameEndsLike(@Param("ename") String ename);
	
	// List<Emp> findByEnameContains(String ename);
	@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%',:ename,'%')")
	List<Emp> empEnameLike(@Param("ename") String ename);
	
	// List<Emp> findBySalGreaterThanEqual(int sal);
	@Query("SELECT e FROM Emp e WHERE e.sal >= :sal")
	List<Emp> empSalGreaterThanEqual(@Param("sal") int sal);
	
	// List<Emp> findBySalLessThanEqual(int sal);
	@Query("SELECT e FROM Emp e WHERE e.sal <= :sal")
	List<Emp> empSalLessThanEqual(@Param("sal") int sal);
	
	// List<Emp> findBySalBetween(int min,int max);
	@Query("SELECT e FROM Emp e WHERE e.sal BETWEEN :min AND :max")
	List<Emp> empSalBetween(@Param("min") int min,@Param("max") int max);
	
	// List<Emp> findByJobAndSalGreaterThan(String job,int sal);
	@Query("SELECT e FROM Emp e WHERE e.job=:job AND e.sal>:sal")
	List<Emp> empJobAndSalGreaterThan(@Param("job") String job,@Param("sal") int sal);
	
	// List<Emp> findByJobOrEname(String job,String ename);
	@Query("SELECT e FROM Emp e WHERE e.job=:job OR e.ename=:ename")
	List<Emp> empJobOrEname(@Param("job") String job,@Param("ename") String ename);
	
	// JOIN
	// List<Emp> findByDeptDname(String dname);
	@Query("SELECT e FROM Emp e JOIN e.dept d WHERE d.dname=:dname")
	List<Emp> empDeptDname(@Param("dname") String dname);
	
	// List<Emp> findByDeptDnameContains(String dname);
	@Query("SELECT e FROM Emp e JOIN e.dept d WHERE d.dname LIKE CONCAT('%',:dname,'%')")
	List<Emp> empDeptDnameLike(@Param("dname") String dname);
	
	// List<Emp> findByOrderBySalDesc();
	@Query("SELECT e FROM Emp e ORDER BY e.sal DESC")
	List<Emp> empOrderbySalDesc();
	
	// List<Emp> findTop3ByOrderBySalDesc();
	//@Query("SELECT e FROM Emp e "
	//		+ "ORDER BY e.sal DESC")
	//List<Emp> empTop3OrderbySalDesc();
	
	// List<Emp> findDistinctByJob(String job);
	//@Query("SELECT DISTINCT e.job FROM Emp e")
	//List<Emp> empDistinctByJob();
	
	// List<Emp> findByCommIsNull();
	@Query("SELECT e FROM Emp e WHERE comm IS NULL")
	List<Emp> empCommIsNull();
	
	// List<Emp> findByJobNot(String job);
	@Query("SELECT e FROM Emp e WHERE e.job<>:job")
	List<Emp> empJobNot(@Param("job") String job);
	
	// List<Emp> findByDeptDeptnoIn(List<Integer> deptnos);
	@Query("SELECT e FROM Emp e JOIN e.dept WHERE e.dept.deptno IN :deptnos")
	List<Emp> empDeptnoIn(@Param("deptnos") List<Integer> deptnos);
	
	/*
	List<Emp> findByDeptLoc(String loc);
	// 부서명 Like
	List<Emp> findByDeptLocContains(String loc);
	
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
	 */
}
