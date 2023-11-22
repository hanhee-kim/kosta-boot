package com.kosta.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.board.entiry.Member;
import com.kosta.board.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/memInfo")
	public ResponseEntity<Object> memberInfo(@RequestParam("id")String id){
		try {
			Member member = boardService.memberInfo(id);
			if(member==null) throw new Exception("아이디 오류");
			return new ResponseEntity<Object>(member,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody Map<String,String> param){
		try {
			Boolean isLogin = boardService.login(param.get("id"), param.get("password"));
			return new ResponseEntity<Boolean>(isLogin,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PostMapping("/join")
	public ResponseEntity<Boolean> join(@RequestBody Member member){
		try {
			boardService.join(member);
			//원래 member 테이블에 존재하면 업데이트 진행됨.
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		
	}

	

	//JSON방식으로 날라온 데이터를 받으려면 맵이나 클래스로 받아야함
	@PostMapping("/memName")
	public ResponseEntity<String> memberName(@RequestBody Map<String,String> param){
		try {
			String name = boardService.memberName(param.get("id"));
			if(name==null) throw new Exception("아이디 오류");
			return new ResponseEntity<String>(name,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
}
