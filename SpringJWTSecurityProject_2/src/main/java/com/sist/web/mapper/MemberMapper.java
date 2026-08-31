package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
/*
 *    - 자바
 *     - Wrapper 클래스
 *     - 제네릭
 *     - 컬렉션
 *     - 예외 처리 종류
 *     
 *    - 자바스크립트
 *     - 클로저
 *     - 호이스팅
 *     
 *    - 오라클
 *     - JOIN
 *     - SubQuery
 *     
 *    - 스프링
 *     - IoC
 *     - DI
 *     - MVC
 *     - Model1, Model2
 *     
 *    - Redis
 *    - React
 */

import com.sist.web.vo.MemberVO;
@Mapper
@Repository
public interface MemberMapper {
	@Select("SELECT userid,username,userpwd,enable,sex "
			+ "FROM springmember "
			+ "WHERE userid=#{userid}")
	public MemberVO findByUserId(String userid);
}
