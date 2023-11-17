package com.kosta.bank.service;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;

public interface MemberService {
	void joinMember(MemberDto member) throws Exception;
	MemberDto loginMember(String id,String password) throws Exception;
	
}
