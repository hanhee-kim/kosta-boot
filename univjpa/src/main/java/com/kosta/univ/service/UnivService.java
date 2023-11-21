package com.kosta.univ.service;

import java.util.List;

import com.kosta.univ.entity.Department;
import com.kosta.univ.entity.Professor;
import com.kosta.univ.entity.Student;

public interface UnivService {
	//학생 이름으로 학생목록 조회
	List<Student> studentListByName(String studName) throws Exception;
	//제 1전공으로 학생목록 조회
	List<Student>  studentListInDept1ByDeptName(String Deptname) throws Exception;
	//제 2전공으로 학생목록 조회
	List<Student> studentListInDept2ByDeptName(String Deptname) throws Exception;
	//학년으로 학생목록 조회
	List<Student> studentListByGrade(Integer grade)throws Exception;
	// 담당교수가 없는 학생목록 조회
	List<Student> studentListByNoProfessor()throws Exception;
	
	//학번으로 학생 조회
	Student studentByStudno(Integer studno) throws Exception;
	//주민번호로 학생 조ㅓ회
	Student studentByJumin(String jumin) throws Exception;
	
	//교수번호로 담당 학생목록 조회
	List<Student> studentListByProfNo(Integer profNo) throws Exception;
	//교수이름으로 담당 학생목록 조회
	List<Student> studentListByProfName(String profName) throws Exception;
	
	//교수번호로 교수 조회
	Professor professorByProfNo(Integer profNo) throws Exception;
	//학과번호로 교수목록 조회
	List<Professor> professorListByProfName(String profName) throws Exception;
	//학과번호로 교수목록 조회
	List<Professor> professorListByDeptNo(Integer deptNo) throws Exception;
	//학과이름으로 교수목록 조회
	List<Professor> professorListByDeptName(String deptName) throws Exception;
	//직급으로 교수목록 조회
	List<Professor> professorListByPosition(String position) throws Exception;
	
	//학과번호로 학과 조회
	Department departmentByDeptNo(Integer deptNo) throws Exception;
	//학과이름으로 학과 조회
	Department departmentByDeptName(String deptName) throws Exception;
	//학부(part)로 학과목록 조회
	List<Department> departmentListByPart(Integer part) throws Exception;
	//위치하는 건물로 학과목록 조회
	List<Department> departmentListByBuild(String build) throws Exception;
}
