package com.kosta.bank.service;

import com.kosta.bank.dto.Member;

public interface MemberService {
	void join(Member member)throws Exception;
	Member login(String id,String password) throws Exception;
}
