package com.kosta.sec.controller;

import java.util.Iterator;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosta.sec.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

@Controller
public class IndexController {
	
	//비밀번호 암호화
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@GetMapping({"","/"})
	@ResponseBody
	public String index() {
		return "인덱스 페이지입니다.";
	}
	
	@GetMapping("/user")
	@ResponseBody
	public String user(@AuthenticationPrincipal PrincipalDetails principal) { //principaldeatilsServie에서 넘긴 세션을 @사용해서 가져다씀
		System.out.println("principal : "+ principal);
		System.out.println(principal.getPassword());
		System.out.println(principal.getUsername());
		Iterator<? extends GrantedAuthority> iter = principal.getAuthorities().iterator();
		while(iter.hasNext()) {
			GrantedAuthority auth = iter.next();
			System.out.println(auth.getAuthority());
		}
		return "user 페이지입니다.";
	}
//	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //메소드 호출전에 권한부여 
//	@PostAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //메소드 호출 직후에 권한부여..??????
//	@Secured("ROLE_MANAGER") 
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "admin 페이지입니다.";
	}
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "manager 페이지입니다.";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	//내가 만든 
	@GetMapping("/loginProc")
	public String loginProc(String username,String password) {
		System.out.println(username);
		System.out.println(password);
		return "redirect:/";
	}
	@GetMapping("/join")
	public String joinForm() {
		return "join";
	}
	
	@PostMapping("/joinProc")
	public String joinProc(User user) {
		String rawPassword = user.getPassword();
		String encodePassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setRoles("ROLE_USER");
		user.setPassword(encodePassword);
		userRepository.save(user);
		System.out.println(user+" , "+encodePassword);
		return "redirect:/login";
	}

}
