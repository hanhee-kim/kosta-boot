package com.kosta.sec.controller;

import java.util.List;

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
	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "회원가입완료";
	}
	
	@GetMapping("/user")
	public String user(Authentication authentication) {	//세션에 넣어놨기 때문에 가져올수 있음
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println(principalDetails.getUser().getId());
		System.out.println(principalDetails.getUser().getUsername());
		System.out.println(principalDetails.getUser().getPassword());
		System.out.println(principalDetails.getUser().getEmail());
		System.out.println(principalDetails.getUser().getRoles());
		return "user";
	}
	
	@GetMapping("/manager/reports")
	public String reports(Authentication authentication) {
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println(principalDetails.getUser().getId());
		System.out.println(principalDetails.getUser().getUsername());
		System.out.println(principalDetails.getUser().getPassword());
		System.out.println(principalDetails.getUser().getEmail());
		System.out.println(principalDetails.getUser().getRoles());
		return "manager/reports";
	}
	
	@GetMapping("/admin/users")
	public List<User> users(){
		return userRepository.findAll();
	}
	
	//해당 페이지의 접근권한이 제어된다면 403 에러가 난다.
	//이는 컨트롤러로 오기전 시큐리티 컨피그에서 설정한 권한으로 토큰으로 권한을 판단하여 에러발생
}
