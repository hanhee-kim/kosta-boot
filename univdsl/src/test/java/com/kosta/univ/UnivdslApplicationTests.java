package com.kosta.univ;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
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
	
//	 * //학생--------------------------------------------
//	//1.학생입학 (test 성공)
//	void enterStudent(Student stud)throws Exception;
	@Test
	void insert1() {
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
//	//2.학번으로 학생 정보 조회(test)
//	Student getStudentByNo(Integer studno)throws Exception;
	@Test
	void select2() {
		try {
			Student s = uService.getStudentByNo(9411);
			System.out.println(s);
			System.out.println("결과:서진수");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
//	//3.학번으로 학생 정보 조회(학과명 포함)(test)
//	Map<String,Object> getstudentByNoWithDeptName(Integer studno)throws Exception;
	@Test
	void select3() {
		try {
			Map<String,Object> res = uService.getstudentByNoWithDeptName(9411);
			System.out.println(res.get("studNo"));
			System.out.println(res.get("deptName"));
			System.out.println("결과:9411,컴퓨터공학과");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//4.학번으로 학생 정보 조회(담당교수명 포함)(test)
//	Map<String,Object> getstudentByNoWithProfName(Integer studno)throws Exception;
	@Test
	void select4() {
		try {
			Map<String,Object> res = uService.getstudentByNoWithProfName(9411);
			System.out.println(res.get("studNo"));
			System.out.println(res.get("profName"));
			System.out.println("결과:9411,조인형");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//5.학번으로 학생 정보 조회( 학과명,담당교수명 포함)(test)
//	Map<String,Object> getstudentByNoWithDeptNameAndProfName(Integer studno)throws Exception;
	@Test
	void select5() {
		try {
			Map<String,Object> res = uService.getstudentByNoWithDeptNameAndProfName(9411);
			System.out.println(res.get("studNo"));
			System.out.println(res.get("deptName"));
			System.out.println(res.get("profName"));
			System.out.println("결과:9411,컴퓨터공학과,조인형");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//6.학생이름으로 학생 정보 조회(test)
//	List<Student> getStudentByName(String name)throws Exception;
	@Test
	void select6() {
		try {
			List<Student> list = uService.getStudentByName("서진수");
			System.out.println("서진수 학생:"+list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//7.특정학과 학생 조회(학과번호로)(test)
//	List<Student> getStudentByDeptNo(Integer deptno)throws Exception;
	@Test
	void select7() {
		try {
			List<Student> list = uService.getStudentByDeptNo(101);
			System.out.println("101학과의 학생:"+list);
			System.out.println("101학과의 학생수(5명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//8.특정학과 학생 조회(학과명으로)(test)
//	List<Student> getStudentByDeptName(String deptName)throws Exception;
	@Test
	void select8() {
		try {
			List<Student> list = uService.getStudentByDeptName("멀티미디어공학과");
			System.out.println("멀티미디어공학과의 학생:"+list);
			System.out.println("멀티미디어공학과의 학생수(4명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//9.특정 학년 학생 조회(test)
//	List<Student> getStudentByGrade(Integer grade)throws Exception;
	@Test
	void select9() {
		try {
			List<Student> list = uService.getStudentByGrade(4);
			System.out.println("4학년의 학생:"+list);
			System.out.println("4학년의 학생수(5명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//10.특정 학과,특정 학년 학생 조회(test)
//	List<Student> getStudentByDeptNameAndGrade(String deptName,Integer grade)throws Exception;
	@Test
	void select10() {
		try {
			List<Student> list = uService.getStudentByDeptNameAndGrade("멀티미디어공학과",4);
			System.out.println("멀티미디어공학과 4 학년:"+list);
			System.out.println("멀티미디어공학과 4 학년의 학생수(1명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//11.주전공이 deptno1이거나 부전공이 deptno2인 학생 조회(test)
//	List<Student> getStudentByDeptno1OrDeptno2(Integer deptno1,Integer deptno2)throws Exception;
	@Test
	void select11() {
		try {
			List<Student> list = uService.getStudentByDeptno1OrDeptno2(301,101);
			System.out.println("301,101전공 :"+list);
			System.out.println("301,101전공의 학생수(2명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//12.주전공(deptno1)이나 부전공(deptno2)이 특정학과인 학생 조회(test)
//	List<Student> getStudentByDeptno1OrDeptno2(Integer deptno)throws Exception;
	@Test
	void select12() {
		try {
			List<Student> list = uService.getStudentByDeptno1OrDeptno2(101);
			System.out.println("101전공 :"+list);
			System.out.println("101전공(부전공)의 학생수(5명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//13.주전공이름이 deptName1이거나 부전공이 deptName2인 학생 조회(test)
//	List<Student> getStudentByDeptName1OrDeptName2(String deptName1,String deptName2)throws Exception;
	@Test
	void select13() {
		try {
			List<Student> list = uService.getStudentByDeptName1OrDeptName2("컴퓨터공학과","전자공학과");
			System.out.println("전공 컴퓨터공학과 ,부전공 전자공학과 :"+list);
			System.out.println("의 학생수(5명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//14.주전공이나 부전공이 특정학과이름인 학생 조회(test)
//	List<Student> getStudentByDeptName1OrDeptName2(String deptName)throws Exception;
	@Test
	void select14() {
		try {
			List<Student> list = uService.getStudentByDeptName1OrDeptName2("컴퓨터공학과");
			System.out.println("전공, 부전공이 컴퓨터공학과  :"+list);
			System.out.println("의 학생수(5명):"+list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			List<Student> list = uRepo.findStudentListByDeptName1OrDeptName2("컴퓨터공학과");
//			System.out.println(list);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
//	//15.특정 교수 담당 학생 목록 조회(test)
//	List<Student> getStudentByProfessorNo(Integer profno) throws Exception;
	@Test
	void select15() {
		try {
			List<Student> list = uService.getStudentByProfessorNo(1001);
			System.out.println(list);
			System.out.println(list.size());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	
//	//교수-----------------------------------------------------
//	//16.교수 입사(test)
//	void enterProfessor(Professor prof) throws Exception;
	@Test
	void select16() {
		Professor pr = new Professor();
		pr.setProfno(7777);
		pr.setId("testprof");
		pr.setName("테스트");
		pr.setPay(1000);
		pr.setPosition("테스트");
		pr.setDeptno(100);
		pr.setHiredate(new Date(2323,11,24));
		try {
			uService.enterProfessor(pr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	//17.교수번호로 교수 정보 조회(test)
//	Professor getProfessorByProfno(Integer profno) throws Exception;
	@Test
	void select17() {
		try {
			Professor p =uService.getProfessorByProfno(1001);
			System.out.println("조인형 나와줘"+p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	//18.교수명으로 교수 정보 조회(test)
//	List<Professor> getProfessorByProfName(String name) throws Exception;
	@Test
	void select18() {
		try {
			List<Professor> p =uService.getProfessorByProfName("주승재");
			System.out.println("주승재 나와줘"+p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	//19.교수번호로 교수정보 조회(학과명 포함)(test)
//	Map<String,Object> getProfessorByProfnoWithDeptName(Integer profno) throws Exception;
	@Test
	void select19() {
		try {
			Map<String,Object> res = uService.getProfessorByProfnoWithDeptName(4005);
			System.out.println(res.get("profNo"));
			System.out.println(res.get("deptName"));
			System.out.println("바비:4005,화학공학과");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	//20.학생의 담당교수 조회(test)
//	Professor getProfessorByStudno(Integer studno) throws Exception;
	@Test
	void select20() {
		try {
			Professor p = uService.getProfessorByStudno(9515);
			System.out.println("심슨나와줘");
			System.out.println(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//21.특정 학과 교수 정보 조회(학과번호로)(test)
//	List<Professor> getProfessorByDeptno(Integer deptno) throws Exception;
	@Test
	void select21() {
		try {
			List<Professor> p = uService.getProfessorByDeptno(201);
			System.out.println("201의 교수들 심슨,최슬기");
			System.out.println(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//22.특정학과 교수 정보 조회(학과명으로)(test)
//	List<Professor> getProfessorByDeptName(String deptName) throws Exception;
	@Test
	void select22() {
		try {
			List<Professor> p = uService.getProfessorByDeptName("전자공학과");
			System.out.println("전자공학과의 교수들 심슨,최슬기");
			System.out.println(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//	//학과-----------------------------------------------------
//	//23.학과 신설(test)
//	void foundDepartment(Department dept) throws Exception;
	@Test
	void select23() {
		Department d = new Department();
		d.setDeptno(999);
		d.setDname("테스트학과");
		try {
			uService.foundDepartment(d);
			System.out.println("insert Department!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//24.학과번호로 학과 조회(test)
//	Department getDepartmentByDeptno(Integer deptno) throws Exception;
	@Test
	void select24() {
		try {
			Department d = uService.getDepartmentByDeptno(102);
			System.out.println("102 멀티미디어공학과");
			System.out.println(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//25.학과명으로 학과 조회(test)
//	Department getDepartmentByDeptName(String name) throws Exception;
	@Test
	void select25() {
		try {
			Department d = uService.getDepartmentByDeptName("멀티미디어공학과");
			System.out.println("102 멀티미디어공학과");
			System.out.println(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//26.학번으로 학과 정보 조회( test)
//	Department getDepartmentByStudNo(Integer studno) throws Exception;
	@Test
	void select26() {
		try {
			Department d = uService.getDepartmentByStudNo(9414);
			System.out.println("9414학번의 김재수의 학과정보,전자공학과");
			System.out.println(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	//27.건물로 학과 조회
//	List<Department> getDepartmentByBuild(String build) throws Exception;
	@Test
	void select27() {
		try {
			List<Department> d = uService.getDepartmentByBuild("정보관");
			System.out.println("정보관의 학과,컴퓨터공학과");
			System.out.println(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//
//	@Test
//	void test() {
//		try {
//			Map<String,Object> res = uService.getstudentByNoWithDeptName(9412);
//			System.out.println("studno :"+res.get("studno")+",deptName :"+res.get("deptName"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	void test2() {
//		try {
////			List<Student> stuList = stuRepo.findByDeptno1OrDeptno2(201);
//			List<Student> stuList = uService.getStudentByDeptNo(201);
//			System.out.println(stuList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	void find1DeptName() {
//		List<Student> stuList;
//		try {
//			stuList = uRepo.findStudentByDeptName1OrDeptName2("전자공학과");
//			System.out.println(stuList.size());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Test
//	void findStudentByDeptName1OrDeptName2() {
//		try {
//			List<Student> stuList = uRepo.findStudentByDeptName1OrDeptName2("컴퓨터공학과", "전자공학과");
//			System.out.println(stuList);
//			System.out.println(stuList.size());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	void findByStudentProfNo() {
//		try {
//			List<Student> stuList = uService.getStudentByProfessorNo(1002);
//			System.out.println(stuList.size());
//			System.out.println(stuList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	void getstudentByNoWithDeptNameAndProfName() {
//		Map<String,Object> res = new HashMap<String, Object>();
//		try {
//			res = uService.getstudentByNoWithDeptNameAndProfName(9412);
//			System.out.println(res);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
