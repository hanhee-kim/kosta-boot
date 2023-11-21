package com.kosta.univ.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
//	Department findByName(String dname);
	Optional<Department> findByDname(String dname);
	
}
