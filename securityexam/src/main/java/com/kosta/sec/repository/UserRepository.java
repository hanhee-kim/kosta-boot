package com.kosta.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.sec.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String name);
}
