package com.kosta.univ;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.StudentRepository;
import com.kosta.univ.repository.UnivRepository;
import com.kosta.univ.service.UnivService;

@SpringBootTest
class UnivdslApplicationTests {
	@Autowired
	UnivRepository uRepo;
	@Autowired
	UnivService uService;
	@Autowired
	StudentRepository stuRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	void insertStudent() {
		try {
			uService.enterStudent(Student.builder()
					.studno(9999)
					.name("테스트")
					.id("test1")
					.grade(1)
					.jumin("1234567890123")
					.birthday(new Date(2023, 11, 23))
					.tel("010)010-0000")
					.height(200)
					.weight(100)
					.deptno1(102)
					.deptno2(201)
					.profno(4007)
					.build()
					);
			System.out.println("insert??");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void test() {
		try {
			Map<String,Object> res = uService.getstudentByNoWithDeptName(9412);
			System.out.println("studno :"+res.get("studno")+",deptName :"+res.get("deptName"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void test2() {
		try {
//			List<Student> stuList = stuRepo.findByDeptno1OrDeptno2(201);
			List<Student> stuList = uService.getStudentByDeptNo(201);
			System.out.println(stuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void find1DeptName() {
		List<Student> stuList;
		try {
			stuList = uRepo.findStudentByDeptName1OrDeptName2("전자공학과");
			System.out.println(stuList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	void findStudentByDeptName1OrDeptName2() {
		try {
			List<Student> stuList = uRepo.findStudentByDeptName1OrDeptName2("컴퓨터공학과", "전자공학과");
			System.out.println(stuList);
			System.out.println(stuList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	void findByStudentProfNo() {
		try {
			List<Student> stuList = uService.getStudentByProfessorNo(1002);
			System.out.println(stuList.size());
			System.out.println(stuList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void getstudentByNoWithDeptNameAndProfName() {
		Map<String,Object> res = new HashMap<String, Object>();
		try {
			res = uService.getstudentByNoWithDeptNameAndProfName(9412);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
