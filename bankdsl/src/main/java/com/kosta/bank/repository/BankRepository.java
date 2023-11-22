package com.kosta.bank.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.bank.entity.Account;
import com.kosta.bank.entity.Member;
import com.kosta.bank.entity.QAccount;
import com.kosta.bank.entity.QMember;
//import com.kosta.bankentity.QAccount;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BankRepository {

	   // Q엔터티의 필드를 이용해 JPAQueryFactory가 제공하는 쿼리 메서드를 실행하기
	   
	   @Autowired
	   private JPAQueryFactory jpaQueryFactory;
	   
	   @Autowired
	   private AccountRepository accountRepository;
	   @Autowired
	   private MemberRepository memberRepository;
	   
	   @Autowired
		EntityManager entityManager;
	   
	   
	   
	   // 계좌 조회
	   public Account findAccountById(String id) {
	      
	      QAccount account = QAccount.account;
	      return jpaQueryFactory
	            .select(account)
	            .from(account)
	            .where(account.id.eq(id))
	            .fetchOne();      
	      
	   }
	   
	   // 전체 계좌 조회
	   public List<Account> findAllAccount() {
	      QAccount account = QAccount.account;
	      return jpaQueryFactory.select(account)
	            .from(account)
	            .fetch();
	   }
	   
	   //입출금
	   @Transactional
	   public void updateBalance(String id,Integer balance) {
		   QAccount account = QAccount.account;
		   jpaQueryFactory.update(account)
		   		.set(account.balance, balance)
		   		.where(account.id.eq(id))
		   		.execute();
		   entityManager.flush();
		   entityManager.clear();
	   }
	   
	   public void insertAccount(Account acc) {
		   accountRepository.save(acc);
	   }
	   
	   public Member selectMemberById(String id) {
		   QMember member = QMember.member;
		   return jpaQueryFactory.select(member)
				   .from(member)
				   .where(member.id.eq(id))
				   .fetchOne();
	   }
	   
	   public Member selectMemberByIdAndPassword(String id,String password) {
		   QMember member = QMember.member;
		   return jpaQueryFactory.select(member)
				   .from(member)
				   .where(member.id.eq(id).and(member.password.eq(password)))
				   .fetchOne();
	   }
	   
	  


}
