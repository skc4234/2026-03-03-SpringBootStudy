package com.sist.web.controller;
import com.sist.web.entity.*;
import com.sist.web.repository.*;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class EmpController {
	private final EmpMethodRepository eDao;
	private final EmpJPQLRepository eDao2;
	private final EntityManager em;
	private final EmpQueryRepository eDao3;
	
	@GetMapping("/emp")
	public void emp_method() {
		//Emp emp=eDao.findByEmpno(7788);
		int index=1;
		//List<Emp> list=eDao.findByEnameStartsWith("A");
		//List<Emp> list=eDao.findByEnameEndsWith("N");
		//List<Emp> list=eDao.findByEnameContains("K");
		//List<Emp> list=eDao.findBySalGreaterThanEqual(3000);
		//List<Emp> list=eDao.findBySalLessThanEqual(1000);
		//List<Emp> list=eDao.findByOrderBySalDesc();
		//List<Emp> list=eDao.findByJobAndSalGreaterThan("SALESMAN", 1000);
		//List<Emp> list=eDao.findByDeptDeptnoIn(List.of(40));
		//List<Emp> list=eDao.findByDeptDnameContains("개");
		//List<Emp> list=eDao2.empListData();
		//Emp emp=eDao2.empDetailData(7839);
		//List<Emp> list=eDao2.empEnameFind("SCOTT");
		//List<Emp> list=eDao2.empEnameStartsLike("A");
		//List<Emp> list=eDao2.empEnameEndsLike("N");
		//List<Emp> list=eDao2.empEnameLike("A");
		//List<Emp> list=eDao2.empSalGreaterThanEqual(3000);
		//List<Emp> list=eDao2.empSalLessThanEqual(1000);
		//List<Emp> list=eDao2.empSalBetween(2000, 3000);
		//List<Emp> list=eDao2.empJobAndSalGreaterThan("SALESMAN", 1000);
		//List<Emp> list=eDao2.empDeptDname("개발팀");
		//List<Emp> list=eDao2.empDeptDnameLike("개");
		//List<Emp> list=eDao2.empOrderbySalDesc();
		//String jpql="SELECT e FROM Emp e "
		//		+ "ORDER BY sal DESC";
		//List<Emp> list=em.createQuery(jpql,Emp.class).setMaxResults(3).getResultList();
		
		//String jpql="SELECT DISTINCT e.job FROM Emp e";
		//List<String> list=em.createQuery(jpql,String.class).getResultList();
		
		//List<Emp> list=eDao2.empCommIsNull();
		//List<Emp> list=eDao2.empJobNot("CLERK");
		//List<Emp> list=eDao2.empDeptnoIn(List.of(10,20));
		
		//Emp emp=eDao3.findByEmpno(7788);
		//List<Emp> list=eDao3.findByEname("SCOTT");
		//List<Emp> list=eDao3.findByEnameStartsWith("A");
		//List<Emp> list=eDao3.findByEnameEndsWith("N");
		//List<Emp> list=eDao3.findByEnameContains("K");
		//List<Emp> list=eDao3.findBySalGreaterThanEqual(3000);
		//List<Emp> list=eDao3.findBySalLessThanEqual(3000);
		//List<Emp> list=eDao3.findByJobAndSalGreaterThan("SALESMAN", 1000);
		//List<Emp> list=eDao3.findByJobNot("SALESMAN");
		//List<Emp> list=eDao3.findByDeptDeptnoIn(List.of(10));
		//List<Emp> list=eDao3.findByOrderBySalDesc();
		//List<String> list=eDao3.findDistinctByJob();
		//List<Emp> list=eDao3.findTop3ByOrderBySalDesc();
		//List<Emp> list=eDao3.findByDeptDname("개발팀");
		//List<Emp> list=eDao3.findByDeptDnameLike("개");
		List<Emp> list=eDao3.findByDeptLoc("서울 강남구");
		for(Emp emp:list) {
			System.out.println("▣ Emp"+index+": "+emp.getEmpno()+" / "+
								emp.getEname()+" / "+
					 			emp.getJob()+" / "+
								emp.getHiredate()+" / "+
								emp.getSal()+" / "+emp.getDept().getDname());
			index++;
		}
		//for(String job:list) {
		//	System.out.println(job);
		//}
		//for(int sal:list) {
		//	System.out.println(sal);
		//}
		//System.out.println(list);
	}
}
