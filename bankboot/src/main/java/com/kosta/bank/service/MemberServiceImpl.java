package com.kosta.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.bank.dao.MemberDao;
import com.kosta.bank.dto.Member;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDao memberDao;
	
	public Boolean idCheck(String id) throws Exception{
		Member member = memberDao.selectMember(id);
		if(member == null) {
			return true; //id 사용가능
		}else {
			return false; //id 사용불가
		}
		
	}
	
	@Override
	public void join(Member member) throws Exception {
		if(idCheck(member.getId())) {
			memberDao.insertMember(member);		
		} else {
			throw new Exception("ID중복");
		}
	}

	@Override
	public Member login(String id, String password) throws Exception {
		Member member = memberDao.selectMember(id);
		if(member == null) {
			throw new Exception("ID가 일치하지 않음");
		} else {
			if(!member.getPassword().equals(password)) {
				throw new Exception("PASSWORD일치하지 않음");
			} else {
				return member;
			}
		}
	}


}
