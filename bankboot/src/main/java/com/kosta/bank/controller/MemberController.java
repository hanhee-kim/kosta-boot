package com.kosta.bank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Member;
import com.kosta.bank.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@PostMapping("/login")
	public ModelAndView login(@RequestParam("id")String id,@RequestParam("password")String password) {
		ModelAndView mav = new ModelAndView();
		try {
			Member member = memberService.login(id, password);
			member.setPassword("");
			mav.addObject("user", member);
			session.setAttribute("member", member);
			mav.setViewName("makeaccount");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public ModelAndView join(HttpServletRequest request , SessionStatus se,@ModelAttribute Member member) {
		ModelAndView mav = new ModelAndView();
		try {
			memberService.join(member);
			mav.addObject("id", member.getId());
			mav.addObject("password", member.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
			mav.setViewName("error");
		}
		//redirect를 사용하지않으면 파라미터를 공유하기 때문에,
		//Member의 객체가 세션이 넣지 않아도 헤더에서 나오는 문제가 발생했었다.
		//session이나 sessionstatus,request 같은 영역 개념이 아닌 단순 파라미터 공유 개념
		//따라서 아예 분리를 시켜주기 위해서 회원가입시 리다이렉트를 사용하여 error가 날때의 맵핑을 따로 해준다
		//에러페이지로 이동할때는 모델에 담아도 가져갈 수 없기 떄문에 에러페이지URL에 파라미터로 값을 달아서 넘겨준다(인코딩하여보내줌)
		
		return mav;
	}
	
	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute("member");
		return "main";
	}
	
	
	
}
