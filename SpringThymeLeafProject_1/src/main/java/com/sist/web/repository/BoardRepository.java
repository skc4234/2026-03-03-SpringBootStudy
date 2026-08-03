package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.vo.*;
/*
 *   JPA 단점 : JOIN이 어렵다
 *             SubQuery를 지원하지 않는다
 *   MyBatis(80%) : JPA(20%)
 */
// <className, 자동증가번호 데이터형>
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
	// 상세보기
	public BoardEntity findByNo(int no);
	/*
	 *  - findByNo(int no) : SELECT *
	 *           WHERE no=(int no)
	 *  - findByNameLike(String name)
	 *  	SELECT *
	 *  	WHERE name LIKE (String name)
	 *  - findByNoBetweenAnd(int a,int b)
	 *  - findByNameStartWith(String a) => WHERE name LIKE 'a%'
	 *  - findByNameContains(String a) => WHERE name LIKE '%a%'
	 */
	// 직접 SQL 문장 생성
	@Query(value = "SELECT no,subject,name,TO_CHAR(regdate,'yyyy-MM-dd') as dbday,hit FROM jpaboard ORDER BY no DESC "
			+ "OFFSET :start ROWS FETCH NEXT 10 ROWS ONLY",
			nativeQuery = true) // SQL을 JPQL로 변경없이 수행
	public List<BoardDTO> boardListData(@Param("start") Integer start);
	
	// save: update,insert
	// delete
}
