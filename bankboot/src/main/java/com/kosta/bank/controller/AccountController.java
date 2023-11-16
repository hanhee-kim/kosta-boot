package com.kosta.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/makeaccount")
	public String makeaccount() {
		return "makeaccount";
	}
	
	@PostMapping("/makeaccount")
	public ModelAndView makeAccount(@ModelAttribute Account acc) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.makeAccount(acc);
			Account sacc = accountService.accountInfo(acc.getId());
			mav.addObject("acc", sacc);
			mav.setViewName("accountinfo");
		}catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/deposit")
	public String deposit() {
		return "deposit";
	}
	
	@PostMapping("/deposit")
	public ModelAndView deposit(@ModelAttribute("money")Integer money ,@ModelAttribute("id")String id) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.deposit(id, money);
			mav.addObject("id", id);
			mav.setViewName("forward:/accountinfo");
			
		}catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	
	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	@PostMapping("/withdraw")
	public ModelAndView withdraw(@ModelAttribute("id")String id,@ModelAttribute("money")Integer money) {
		ModelAndView mav = new ModelAndView();
		try {
			accountService.withdraw(id, money);			
			mav.addObject("id", id);
			mav.setViewName("forward:/accountinfo");
			
		}catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	
	@GetMapping("/accountinfo")
	public String accountinfo() {
		return "accountinfoform";
	}
	
	@PostMapping("/accountinfo")
	public ModelAndView accountinfo(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView();
		try {
			Account acc = accountService.accountInfo(id);
			mav.addObject("acc", acc);
			mav.setViewName("accountinfo");
			
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	@GetMapping("/allaccountinfo")
	public ModelAndView allaccountinfo() {
		ModelAndView mav = new ModelAndView();
		
		try {
			List<Account> accs = accountService.allAccountInfo();
			mav.addObject("accs", accs);
			mav.setViewName("allaccountinfo");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
	
	
	
}
