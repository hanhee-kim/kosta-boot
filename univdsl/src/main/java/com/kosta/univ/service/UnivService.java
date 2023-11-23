package com.kosta.univ.service;

import java.util.List;
import java.util.Map;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;

public interface UnivService {
	//학생--------------------------------------------
	//1.학생입학
	void enterStudent(Student stud)throws Exception;
	//2.학번으로 학생 정보 조회
	Student getStudentByNo(Integer studno)throws Exception;
	//3.학번으로 학생 정보 조회(학과명 포함)
	Map<String,Object> getstudentByNoWithDeptName(Integer studno)throws Exception;
	//4.학번으로 학생 정보 조회(담당교수명 포함)
	Map<String,Object> getstudentByNoWithProfName(Integer studno)throws Exception;
	//5.학번으로 학생 정보 조회( 학과명,담당교수명 포함)
	Map<String,Object> getstudentByNoWithDeptNameAndProfName(Integer studno)throws Exception;
	//6.학생이름으로 학생 정보 조회
	List<Student> getStudentByName(String name)throws Exception;
	//7.특정학과 학생 조회(학과번호로)
	List<Student> getStudentByDeptNo(Integer deptno)throws Exception;
	//8.특정학과 학생 조회(학과명으로)
	List<Student> getStudentByDeptName(String deptName)throws Exception;
	//9.특정 학년 학생 조회
	List<Student> getStudentByGrade(Integer grade)throws Exception;
	//10.특정 학과,특정 학년 학생 조회
	List<Student> getStudentByDeptNameAndGrade(String deptName,Integer grade)throws Exception;
	//11.주전공이 deptno1이거나 부전공이 deptno2인 학생 조회
	List<Student> getStudentByDeptno1OrDeptno2(Integer deptno1,Integer deptno2)throws Exception;
	
	//12.주전공(deptno1)이나 부전공(deptno2)이 특정학과인 학생 조회
	List<Student> getStudentByDeptno1OrDeptno2(Integer deptno)throws Exception;
	//13.주전공이름이 deptName1이거나 부전공이 deptName2인 학생 조회
	List<Student> getStudentByDeptName1OrDeptName2(String deptName1,String deptName2)throws Exception;
	//14.주전공이나 부전공이 특정학과이름인 학생 조회
	List<Student> getStudentByDeptName1OrDeptName2(String deptName)throws Exception;
	//15.특정 교수 담당 학생 목록 조회
	List<Student> getStudentByProfessorNo(Integer profno) throws Exception;
	
	//교수-----------------------------------------------------
	//16.교수 입사
	void enterProfessor(Professor prof) throws Exception;
	//17.교수번호로 교수 정보 조회
	Professor getProfessorByProfno(Integer profno) throws Exception;
	//18.교수명으로 교수 정보 조회
	List<Professor> getProfessorByProfName(String name) throws Exception;
	//19.교수번호로 교수정보 조회(학과명 포함)
	Map<String,Object> getProfessorByProfnoWithDeptName(Integer profno) throws Exception;
	//20.학생의 담당교수 조회
	Professor getProfessorByStudno(Integer studno) throws Exception;
	//21.특정 학과 교수 정보 조회(학과번호로)
	List<Professor> getProfessorByDeptno(Integer deptno) throws Exception;
	//22.특정학과 교수 정보 조회(학과명으로)
	List<Professor> getProfessorByDeptName(String deptName) throws Exception;
	
	
	//학과-----------------------------------------------------
	//23.학과 신설
	void foundDepartment(Department dept) throws Exception;
	//24.학과번호로 학과 조회
	Department getDepartmentByDeptno(Integer deptno) throws Exception;
	//25.학과명으로 학과 조회
	Department getDepartmentByDeptName(String name) throws Exception;
	//26.학번으로 학과 정보 조회
	Department getDepartmentByStudNo(Integer studno) throws Exception;
	//27.건물로 학과 조회
	List<Department> getDepartmentByBuild(String build) throws Exception;
	
	
	
	
	
	
	
	
}
