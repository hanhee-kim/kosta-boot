package com.kosta.univ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{
	List<Professor> findByName(String name);
	List<Professor> findByPosition(String position);
}
