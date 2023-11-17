package com.kosta.bank.service;

import java.util.List;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;

public interface AccountService {
	void makeAccount(AccountDto acc)throws Exception;
	Account deposit(String id,Integer money) throws Exception;
	Account withdraw(String id, Integer money) throws Exception;
	AccountDto accountInfo(String id) throws Exception;
	List<Account> allAccountInfo() throws Exception;

}
