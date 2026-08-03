package com.sist.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.web.dao.EmpDAO;
import com.sist.web.vo.EmpVO;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDAO dao;
	@Override
	public List<EmpVO> empListData() {
		// TODO Auto-generated method stub
		// 비즈니스 로직 수행
		return dao.empListData();
	}

}
