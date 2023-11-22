package com.kosta.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bank.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

}
