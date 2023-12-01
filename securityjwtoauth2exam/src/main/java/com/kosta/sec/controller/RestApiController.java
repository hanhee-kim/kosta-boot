package com.kosta.sec.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.sec.config.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RestApiController {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;	

	@GetMapping("user")
	public ResponseEntity<User> user(Authentication authentication) {
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println(principalDetails.getUser().getId());
		System.out.println(principalDetails.getUser().getUsername());
		System.out.println(principalDetails.getUser().getPassword());
		System.out.println(principalDetails.getUser().getRoles());
		return new ResponseEntity<User>(principalDetails.getUser(), HttpStatus.OK);
	}
	
	@GetMapping("manager/reports")
	public String reports() {
		return "reports";
	}

	@GetMapping("admin/users")
	public List<User> users() {
		return userRepository.findAll();
	}
	
	@PostMapping("join")
	public String join(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "회원가입완료";
	}
}
