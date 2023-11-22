package com.kosta.bank;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.BankRepository;

@SpringBootTest
class BankdslApplicationTests {
	@Autowired
	BankRepository bankRepository;

	@Test
	void contextLoads() {
	}

	@Test
	   void findAccountById() {
	      Account acc = bankRepository.findAccountById("10001");
	      System.out.println("결과: " + acc);
	   }

	   @Test
	   void findAllAccount() {
	      List<Account> accs = bankRepository.findAllAccount();
	      System.out.println("-------결과-------");
	      for (Account account : accs) {
	         System.out.println(account);
	      }
	   }
	   @Commit
	   @Test
	   @Transactional
	   void updateBalance() {
		   bankRepository.updateBalance("10001", 400000);
		   Account acc = bankRepository.findAccountById("10001");
		   System.out.println(acc);
	   }

	   @Test
	   @Commit
	   void insertAccount() {
		   Account acc = Account.builder().id("test01")
				   .name("테스트")
				   .balance(100000)
				   .type("normal")
				   .build();
		   bankRepository.insertAccount(acc);
	   }
	   
	   @Test
	   void selMemberById() {
		   Member mem = bankRepository.selectMemberById("10007");
		   System.out.println("ID로 MEMBER찾기 : "+ mem);
	   }
	   
	   @Test
	   void selMemberByIdAndPw() {
		   Member mem = bankRepository.selectMemberByIdAndPassword("10007", "100071");
		   System.out.println("ID와 PW로 MEMBER찾기 : "+ mem);
	   }
	   
	   
}
