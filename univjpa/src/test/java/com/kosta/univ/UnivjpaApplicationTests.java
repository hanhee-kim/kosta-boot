package com.kosta.univ;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Student;
import com.kosta.univ.repository.DepartmentRepository;
import com.kosta.univ.repository.StudentRepository;
import com.kosta.univ.service.UnivService;

@SpringBootTest
class UnivjpaApplicationTests {
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private UnivService univService;

	@Test
	void contextLoads() {
	}
	
	@Test
	void selectStudentByName() {
		List<Student> stus = studentRepository.findByName("구유미");
			System.out.println(stus);
	}
	
	@Test
	void selDepartByName() {
		Optional<Department> dep = departmentRepository.findByDname("컴퓨터공학과");
//		Optional<Department> dep = departmentRepository.findByDname("전자공학과");
		System.out.println(dep.get());
	}
	
	
	@Test
	void selectStuByDeptNo() {
		List<Student> stus = studentRepository.findByDepartment1_deptno(101);
		System.out.println(stus);
	}
	
	@Test
	void selStuByDeptName() {
		try {
//			List<Student> stus = univService.studentListInDept1ByDeptName("컴퓨터공학과");
			List<Student> stus = univService.studentListInDept2ByDeptName("전자공학과");
			if(stus.isEmpty()) {
				System.out.println("stuList 가 NULL");
			}else {
				System.out.println(stus);				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Test
	void selNoProfno() {
		List<Student> stu = studentRepository.findByProfessor_profnoIsNull();
		if(stu.isEmpty()) {
			System.out.println("데이터없음");
		}else {
			System.out.println(stu);
		}
	}
	
//	@Test
//	void noProf() {
//		List<Student> stu = studentRepository.findByProfessorNull();
//		System.out.println(stu);
//	}
	
	
	@Test
	void studentByStudno() {
		try {
			Student stu = univService.studentByStudno(9412);
			System.out.println(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //서재수
		
	}
	@Test
	void studentByJumin() {
		try {
			Student stu = univService.studentByJumin("7510231901810");
			System.out.println(stu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
