package com.kosta.bank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.access.EjbAccessException;
import org.springframework.stereotype.Service;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;
import com.kosta.bank.repository.MemberRepository;
@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberRepository memRepo;

	@Override
	public void joinMember(MemberDto member) throws Exception {
		Optional<Member> oMember = memRepo.findById(member.getId());
		if(oMember.isPresent()) {
			throw new Exception("ID중복");
		}
//		memRepo.save(member);
		memRepo.save(Member.toEntity(member));
	}

	@Override
	public MemberDto loginMember(String id, String password) throws Exception {
		Optional<Member> oMember = memRepo.findById(id);
		if(oMember.isEmpty()) {
			throw new Exception("ID존재하지않음.");
		} else {
			if(!oMember.get().getPassword().equals(password)) {
				throw new Exception("비번일치하지않음.");
			} else {
				return MemberDto.toDto(oMember.get());
			}
		}
	}

}
