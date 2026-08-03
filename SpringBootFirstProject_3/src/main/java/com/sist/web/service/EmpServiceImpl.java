package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.mapper.EmpMapper;
import com.sist.web.vo.EmpVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	private final EmpMapper mapper;
	
	@Override
	public List<EmpVO> empListData() {
		// TODO Auto-generated method stub
		return mapper.empListData();
	}

}
