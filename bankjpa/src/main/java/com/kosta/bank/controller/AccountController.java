package com.kosta.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.AccountDto;
import com.kosta.bank.entity.Account;
import com.kosta.bank.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accService;
	
	@PostMapping("/makeaccount")
	public ResponseEntity<String> makeAccount(@RequestBody AccountDto acc){
		try {
			accService.makeAccount(acc);
			return new ResponseEntity<String>(acc.getId()+"계좌가 개설되었습니다.", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/deposit/{id}")
	public ResponseEntity<Object> deposit(@PathVariable("id")String id,
			@RequestBody Map<String,Integer> param){
		//JSON 형태로 올라온걸 객체로 받는다.
		System.out.println("id"+id);
		try {
			Account acc = accService.deposit(id, param.get("money"));
			System.out.println("accService");	
			Map <String,Object> res = new HashMap<String, Object>();
			res.put("id", acc.getId());
			res.put("balance", acc.getBalance());
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Object> withdraw(@RequestBody Map<String, Object> param){
		try {
			String id = (String) param.get("id");
			Integer money = Integer.parseInt((String)param.get("money"));
			Account acc = accService.withdraw(id, money);
			Map<String,Object> res = new HashMap<String, Object>();
			res.put("id", acc.getId());
			res.put("balance", acc.getBalance());
			return new ResponseEntity<Object>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/accountinfo/{id}")
	public ResponseEntity<Object> accountinfo(@PathVariable(name="id") String id){
		try {
			AccountDto acc = accService.accountInfo(id);
			return new ResponseEntity<Object>(acc,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/allaccountinfo")
	public ResponseEntity<Object> allAccountInfo(){
		try {
			List<Account> accList = accService.allAccountInfo();
			return new ResponseEntity<Object>(accList,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
