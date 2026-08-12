package com.sist.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sist.web.entity.*;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpQueryRepository {
	private final JPAQueryFactory queryFactory;
	
	// 1) Query Method : public Emp findByEmpno(int empno);
	// 2) JPQL : @Query("SELECT e FROM Emp e")
	// 3) QueryDSL
	public Emp findByEmpno(int empno) {
		QEmp emp=QEmp.emp;
		return (Emp)queryFactory.from(emp)
				           .where(emp.empno.eq(empno))
				           .fetchOne();
	}
	
	// List<Emp> findByEname(String ename);
	public List<Emp> findByEname(String ename){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				           .where(emp.ename.eq(ename))
				           .fetch();
	}
	
	/*
	 *    from(테이블 : Q-class 객체)
	 *    where(조건 => .eq, .gt 등)
	 *    orderBy(컬럼.desc())
	 *    groupBy(컬럼)
	 *    having(조건)
	 *    fetchOne() => VO
	 *    fetch() => List
	 *    
	 */
	
	// List<Emp> findByEnameStartsWith(String ename);
	public List<Emp> findByEnameStartsWith(String ename){ 
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.ename.startsWith(ename))
				                      .fetch();
	}
	
	// List<Emp> findByEnameEndsWith(String ename);
	public List<Emp> findByEnameEndsWith(String ename){ 
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.ename.endsWith(ename))
				                      .fetch();
	}
	
	//List<Emp> findByEnameContains(String ename);
	public List<Emp> findByEnameContains(String ename){ 
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.ename.contains(ename))
				                      .fetch();
	}
	
	// 비교연산자
	/*
	 *   = : eq    emp.sal.eq(3000) => WHERE sal = 3000
	 *   < : lt    emp.sal.lt(3000) => WHERE sal < 3000
	 *   				LessThan
	 *   > : gt    emp.sal.gt(3000) => WHERE sal > 3000
	 *   				GreaterThan
	 *   <= : loe  emp.sal.loe(3000) => WHERE sal <= 3000
	 *   				LessThanEqual
	 *   >= : goe  emp.sal.goe(3000) => WHERE sal >= 3000
	 *   				GreaterThanEqual
	 *   <> : ne
	 *   
	 *   
	 */
	
	// List<Emp> findBySalGreaterThanEqual(int sal);
	public List<Emp> findBySalGreaterThanEqual(int sal){ 
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.sal.goe(sal))
				                      .fetch();
	}
	
	// List<Emp> findBySalLessThanEqual(int sal);
	public List<Emp> findBySalLessThanEqual(int sal){ 
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.sal.loe(sal))
				                      .fetch();
	}
	
	// List<Emp> findBySalBetween(int min,int max);
	public List<Emp> findBySalBetween(int min,int max){ 
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.sal.between(min, max))
				                      .fetch();
	}
	
	// AND => where(A,B) / where(A.and(B))
	// List<Emp> findByJobAndSalGreaterThan(String job,int sal);
	public List<Emp> findByJobAndSalGreaterThan(String job,int sal){ 
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.job.eq(job),emp.sal.gt(sal))
				                      // .where(emp.job.eq(job).and(emp.sal.gt(sal)))
				                      .fetch();
	}
	
	// OR
	// List<Emp> findByJobOrEname(String job,String ename);
	public List<Emp> findByJobOrEname(String job,String ename){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				                      .where(emp.job.eq(job).or(emp.ename.eq(ename)))
				                      .fetch();
	}
	
	// IS NOT NULL / IS NULL
	// WHERE comm IS NULL
	// List<Emp> findByCommIsNull();
	public List<Emp> findByCommIsNull(){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
                .where(emp.comm.isNull())
                .fetch();
	}
	
	// WHERE comm IS NOT NULL
	// List<Emp> findByCommIsNotNull();
	public List<Emp> findByCommIsNotNull(){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
                .where(emp.comm.isNotNull())
                .fetch();
	}
		
	// IN
	// WHERE deptno IN(?,?,?)
	// List<Integer> deptnos = List.of(10,20,30)
	// List<Emp> findByDeptDeptnoIn(List<Integer> deptnos);
	public List<Emp> findByDeptDeptnoIn(List<Integer> deptnos){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
                .where(emp.dept.deptno.in(deptnos))
                .fetch();
	}
	
	// NOT
	// WHERE job<>?
	// List<Emp> findByJobNot(String job);
	public List<Emp> findByJobNot(String job){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
                .where(emp.job.ne(job))
                .fetch();
	}
	
	// JOIN
	// 부서명(dept 테이블 조인)으로 emp 검색
	// List<Emp> findByDeptDname(String dname);
	public List<Emp> findByDeptDname(String dname){
		QEmp emp=QEmp.emp;
		QDept dept=QDept.dept;
		return (List<Emp>)queryFactory.from(emp)
				.join(emp.dept,dept)
                .where(dept.dname.eq(dname))
                .fetch();
	}
	
	public List<Emp> findByDeptDnameLike(String dname){
		QEmp emp=QEmp.emp;
		QDept dept=QDept.dept;
		return (List<Emp>)queryFactory.from(emp)
				.join(emp.dept,dept)
                .where(dept.dname.contains(dname))
                .fetch();
	}
	
	
	// List<Emp> findByDeptLoc(String loc);
	public List<Emp> findByDeptLoc(String loc){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
                .where(emp.dept.loc.eq(loc))
                .fetch();
	}
	
	// 정렬
	// ORDER BY sal DESC
	// List<Emp> findByOrderBySalDesc();
	public List<Emp> findByOrderBySalDesc(){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
                .orderBy(emp.sal.desc())
                .fetch();
	}

	// Top-N : 정렬 후 상위 n개 row 반환
	// WHERE rownum<=3 ORDER BY sal DESC
	// List<Emp> findTop3ByOrderBySalDesc();
	public List<Emp> findTop3ByOrderBySalDesc(){
		QEmp emp=QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
                .orderBy(emp.sal.desc())
                .limit(3)
                .fetch();
	}
	
	// 중복 제거
	public List<Integer> findDistinctBySal(){
		QEmp emp=QEmp.emp;
		return (List<Integer>)queryFactory.select(emp.sal)
				.distinct()
				.from(emp)
                .fetch();
	}
	
	// List<Emp> findDistinctByJob();
		public List<String> findDistinctByJob(){
			QEmp emp=QEmp.emp;
			return (List<String>)queryFactory.select(emp.job)
					.distinct()
					.from(emp)
	                .fetch();
		}
	
	
	/*
	
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
	 */
}
