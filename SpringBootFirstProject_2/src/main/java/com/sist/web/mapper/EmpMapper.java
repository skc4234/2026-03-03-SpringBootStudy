package com.sist.web.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.*;

@Mapper
@Repository
public interface EmpMapper {
	@Select("SELECT empno,ename,job "
			+ "FROM emp "
			+ "ORDER BY empno ASC")
	public List<EmpVO> empListData();
}
