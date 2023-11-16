package com.kosta.bank.service;

import java.util.List;

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
		Account sacc = accountInfo(acc.getId());
		if(sacc != null) {
			throw new Exception("계좌 중복");
		}
		accountDao.insertAccount(acc);
	}

	@Override
	public Account accountInfo(String id) throws Exception {
		return accountDao.selectAccount(id);
	}

	@Override
	public void deposit(String id, Integer money) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc != null) {
			accountDao.updateBalance(id, acc.getBalance()+money);
		} else {
			throw new Exception("계좌번호없음");
		}
	}

	@Override
	public void withdraw(String id, Integer money) throws Exception {
		Account acc = accountDao.selectAccount(id);
		if(acc != null) {
			if(acc.getBalance()-money < 0 ) {
				throw new Exception("잔액부족");
			}
			accountDao.updateBalance(id, acc.getBalance()-money);
		} else {
			throw new Exception("계좌번호없음");
		}
	}

	@Override
	public List<Account> allAccountInfo() throws Exception {
		List<Account> accs = accountDao.selectAccountList();
		return accs;
	}

}
