package com.kosta.univ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	List<Student> findByName(String name) throws Exception;
//	List<Student> findByDeptno1OrDeptno2(Integer deptno) throws Exception;
	List<Student> findByGrade(Integer grade)throws Exception;
	List<Student> findByProfno(Integer profno) throws Exception;
}
