package com.kosta.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bank.entity.Account;

//public interface AccountRepository extends JpaRepository<어떤엔티티(테이블), 프라이머리키의 타입> {
public interface AccountRepository extends JpaRepository<Account, String> {

}
