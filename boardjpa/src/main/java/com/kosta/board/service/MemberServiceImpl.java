package com.kosta.board.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.board.dto.MemberDto;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.MemberRepository;
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;

	@Override
	public MemberDto login(String id, String password) throws Exception {
		Optional<Member> omember = memberRepository.findById(id);
		if(omember.isEmpty()) throw new Exception("아이디 오류");
		Member member = omember.get();
		if(!member.getPassword().equals(password))throw new Exception("비밀번호 오류");
		
		return member.toDto(); //entity => Dto
	}

	@Override
	public void join(MemberDto memberDto) throws Exception {
		//먼저 있는지 확인
		Optional<Member> omember = memberRepository.findById(memberDto.getId());
		if(omember.isPresent()) throw new Exception("아이디 중복 오류");
		Member member = memberDto.toEntity();  //dto => entity
		memberRepository.save(member);
		
	}

}
