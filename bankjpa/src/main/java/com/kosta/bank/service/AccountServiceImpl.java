package com.kosta.bank.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.repository.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountRepository accountRepositoy;
	//AccountDto는 save를 사용할수 없어서 ModelMapper를 사용해야한다
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public void makeAccount(AccountDto acc) throws Exception {
		Optional<Account> oacc = accountRepositoy.findById(acc.getId());
		if(oacc.isPresent()) throw new Exception("계좌번호 중복 오류");
//		accountRepositoy.save(acc);
		//가져온 DTO acc를 Account 클래스로 변경해줌(반대도 가능하다)
		Account accEntity = modelMapper.map(acc, Account.class);
		accountRepositoy.save(accEntity);
		
	}

	@Override
	public Account deposit(String id, Integer money) throws Exception {
		Optional<Account> oacc = accountRepositoy.findById(id);
		if(oacc.isEmpty()) throw new Exception("계좌 없음.");
		oacc.get().deposit(money);
		accountRepositoy.save(oacc.get());
		
		return oacc.get();
	}


	@Override
	public Account withdraw(String id, Integer money) throws Exception {
		Optional<Account> oacc = accountRepositoy.findById(id);
		if(oacc.isEmpty()) throw new Exception("계좌 없음.");
		oacc.get().withdraw(money);
		accountRepositoy.save(oacc.get());
		
		return oacc.get();
	}


	@Override
	public AccountDto accountInfo(String id) throws Exception {
		Optional<Account> oacc = accountRepositoy.findById(id);
		if(oacc.isEmpty()) throw new Exception("계좌 없음.");
//		return oacc.get();
		AccountDto accDto = modelMapper.map(oacc.get(), AccountDto.class);
		return accDto;
	}


	@Override
	public List<Account> allAccountInfo() throws Exception {
		List<Account> accList = accountRepositoy.findAll();
		return accList;
	}

}
