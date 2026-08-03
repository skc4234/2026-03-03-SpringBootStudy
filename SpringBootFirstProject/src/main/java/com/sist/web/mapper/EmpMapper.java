package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/*
 *   1. Mapper => SQL 문장 만들기 
 *   2. DAO => Mapper 연동 => 데이터베이스 연동
 *   3. Service => 비즈니스 로직, DAO+추가 기능을 하나의 서비스로 제작
 *   4. Controller => 브라우저로 이동/전송
 *   5. JSP / HTML(ThymeLeaf)로 출력
 */
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface EmpMapper {
	@Select("SELECT empno,ename,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,"
			+ "sal,job "
			+ "FROM emp "
			+ "ORDER BY empno ASC")
	public List<EmpVO> empListData();
}
