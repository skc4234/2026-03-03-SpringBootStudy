package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.AuthorityVO;

/*
 *   @Repository : DAO 한개 => 테이블 한개 연결
 *   @Service : 관련된 테이블을 여러개 묶어서 처리
 *   ==> BI
 */

@Mapper
@Repository
public interface AuthorityMapper {
	@Select("SELECT userid,authority "
			+ "FROM authority "
			+ "WHERE userid=#{userid}")
	public List<AuthorityVO> getAutorityData(String userid);
}
