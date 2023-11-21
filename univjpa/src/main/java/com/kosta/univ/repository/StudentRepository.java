package com.kosta.univ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByName(String name);
	List<Student> findByDepartment1_deptno(Integer deptno);
//	List<Student> findByDeptno1(Integer deptno1);
//	List<Student> findByDeptno2(Integer deptno2);
}
