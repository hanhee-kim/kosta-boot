package com.kosta.univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.univ.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer>{

}
