package com.kosta.univ.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	List<Student> findByName(String name);
	List<Student> findByDepartment1_deptno(Integer deptno);
	List<Student> findByGrade(Integer grade);
	List<Student> findByProfessor_profnoIsNull();
//	List<Student> findByProfessorNull();
	Optional<Student> findByJumin(String jumin);
	List<Student> findByProfessor_profno(Integer profno);
	List<Student> findByProfessor_name(String name);
}
