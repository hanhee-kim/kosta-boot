package com.kosta.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
