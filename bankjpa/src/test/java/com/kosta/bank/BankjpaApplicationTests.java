package com.kosta.bank;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.AccountRepository;
import com.kosta.bank.repository.MemberRepository;
import com.kosta.bank.service.AccountService;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BankjpaApplicationTests {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private AccountService accService;
	@Autowired
	private MemberRepository memberRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void selectAccountTest() {
		Optional<Account> oacc = accountRepository.findById("1111");
		//isEmpty()와 반대 즉, 데이터가 있으면
		if(oacc.isPresent()) {
			//oacc.get()은 Optional을 벗기고 Account객체로 받아온다.
			//Account의 toString을 호출한다.
			System.out.println(oacc.get());
		}
	}
	
//	@Test
//	void insertAccountTest() {
//		Account acc = new Account("10013","삼동",300000,"Special","VIP");
//		//insert -> save / update -> save
////		accountRepository.save(acc);
//		try {
//			accService.makeAccount(acc);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	void updateAccountTest() {
		Account acc = new Account("10012","이동",200000,"Special","VIP");
//		accountRepository.save(acc);
		
	}
	
	@Test
	void deleteAccount() {
		accountRepository.deleteById("10012");
	}
	
	@Test
	void selectAllAccountTest() {
		List<Account> accList = accountRepository.findAll();
		for(Account acc : accList) {
			System.out.println(acc);
		}
	}
	
	
	////멤버
	@Test
	void insertMember() {
		Member m = Member.builder().id("coco").name("코코").password("zhzh")
				.email("coco@coco.com").build();
		
		memberRepository.save(m);
	}
}
