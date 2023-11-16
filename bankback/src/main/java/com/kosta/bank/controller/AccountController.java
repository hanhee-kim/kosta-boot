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
import org.springframework.web.bind.annotation.RestController;

import com.kosta.bank.dto.Account;
import com.kosta.bank.service.AccountService;

@RestController
public class AccountController {
//RestController는 안에 들어있는 모든 메소드들이 리턴타입이 데이터이다(뷰X)
	
	@Autowired
	private AccountService accountService;
	
	//화면에서 계좌 개설버튼을 눌렀을때
	//객체로 감싸고 , 에러 여부 또한 반환하는 ResponseEntity
	@PostMapping("/makeaccount")
	public ResponseEntity<String> makeAccount(@RequestBody Account acc){
		//RequestBody : JSON형태의 객체를 객체의 인스턴스타입(이름)만 맞다면 자바 객체로 자동변환되어 가져와준다
		try {
			accountService.makeAccount(acc);
			return new ResponseEntity<String>("계좌가 개설되었습니다.",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/accountinfo/{id}")
	public ResponseEntity<Object> accountInfo(@PathVariable String id){
		try {
			Account acc = accountService.accountInfo(id);
			return new ResponseEntity<Object>(acc,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/allaccountinfo")
	public ResponseEntity<Object> allAccountInfo(){
		try {
			List<Account> accs = accountService.allAccountInfo();
			return new ResponseEntity<Object>(accs,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("계좌 목록 조회 실패",HttpStatus.BAD_REQUEST);
		}
	}
	
//	@PostMapping("/deposit/{id}/{money}")
//	public ResponseEntity<Object> deposit(@PathVariable String id,@PathVariable Integer money){
//		try {
//			accountService.deposit(id, money);
////			Account acc = accountService.accountInfo(id);
//			System.out.println(id);
//			System.out.println(money);
//			return new ResponseEntity<Object>("입금성공",HttpStatus.OK);
//		}catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Object>("입금오류",HttpStatus.BAD_REQUEST);
//		}
//	}
	
	@PutMapping("/deposit/{id}")
	public ResponseEntity<Object> deposit(@PathVariable String id,@RequestBody Map<String,Integer> param){
		try {
			Integer balance = accountService.deposit(id, param.get("money"));
			Map<String,Object> res = new HashMap<String, Object>();
			res.put("id", id);
			res.put("balance", balance);
			return new ResponseEntity<Object>(res,HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("입금 오류",HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Object> withdraw(@RequestBody Map<String,Object> param){
		try {
			String id = (String)param.get("id");
			Integer money = Integer.valueOf((String)param.get("money"));
			System.out.println(id);
			System.out.println(money);
			Integer balance = accountService.withdraw(id, money);
			Map<String,Object> res = new HashMap<String, Object>();
			res.put("id", id);
			res.put("balance", balance);
			return new ResponseEntity<Object>(res,HttpStatus.OK);
			
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("출금오류",HttpStatus.BAD_REQUEST);
		}
	}
	

}
