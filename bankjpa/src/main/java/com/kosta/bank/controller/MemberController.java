package com.kosta.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.MemberDto;
import com.kosta.bank.entity.Member;
import com.kosta.bank.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memService;
	
	@PostMapping("/join")
	public ResponseEntity<Object> join(@RequestBody MemberDto member){
		try {
			memService.joinMember(member);
			return new ResponseEntity<Object>("가입성공",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody Member member){
		try {
			MemberDto loginMember = memService.loginMember(member.getId(), member.getPassword());
			return new ResponseEntity<Object>(loginMember,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
