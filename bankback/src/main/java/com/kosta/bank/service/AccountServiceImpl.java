package com.kosta.bank.service;

import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dao.AccountDao;
import com.kosta.bank.dto.Account;
@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	public void makeAccount(Account acc) throws Exception {
		Account sacc = accountDao.selectAccount(acc.getId());
		if(sacc != null) {
			throw new Exception("계좌 중복");
		}
		accountDao.insertAccount(acc);
	}

	@Override
	public Account accountInfo(String id) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc == null) throw new Exception("계좌번호 없음");
		return acc;
	}

	@Override
	public Integer deposit(String id, Integer money) throws Exception {
		Account acc = accountDao.selectAccount(id);
		acc.deposit(money);
		accountDao.updateBalance(acc.getId(), acc.getBalance());
		return acc.getBalance();
	}

	@Override
	public Integer withdraw(String id, Integer money) throws Exception {
		Account acc = accountDao.selectAccount(id);
		acc.withdraw(money);
		accountDao.updateBalance(acc.getId(), acc.getBalance());
		return acc.getBalance();
	}

	@Override
	public List<Account> allAccountInfo() throws Exception {
		List<Account> accs = accountDao.selectAccountList();
		return accs;
	}

}
