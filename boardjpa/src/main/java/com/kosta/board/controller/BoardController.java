package com.kosta.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"/boardlist/{page}", "/boardlist"})
	public ResponseEntity<Map<String,Object>> boardList(@PathVariable(required = false) Integer page){
//		System.out.println("boardlist-controller 진입");
		try {
			PageInfo pageInfo = new PageInfo(page);
//			pageInfo.setCurPage(curpage==null? 1:curpage);
			List<BoardDto> boards = boardService.boardListByPage(pageInfo);
			Map<String, Object> res = new HashMap<String, Object>();

			res.put("boardList", boards); 
			res.put("pageInfo", pageInfo);
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/boardsearch/{page}/{type}/{keyword}")
	public ResponseEntity<Map<String,Object>> boardSearch(@PathVariable(required = false) Integer page,
			@PathVariable(required = false) String type,@PathVariable(required = false)String keyword){
		try {
			PageInfo pageInfo = new PageInfo(page);
			List<BoardDto> boardList = boardService.searchListByPage(type, keyword, pageInfo);
			Map<String,Object> res = new HashMap<String, Object>();
			res.put("boardList", boardList);
			res.put("pageInfo", pageInfo);
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/boarddelete/{num}")
	public ResponseEntity<Object> boardDelete(@PathVariable Integer num){
		try {
			System.out.println(num);
			boardService.boardDelete(num);
			return new ResponseEntity<Object>(num,HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			//프론트에서는 다시 갱신시킬 필요가 없음
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/boarddetail/{sect}/{num}")
	public ResponseEntity<Map<String,Object>> boardDetail(@PathVariable String sect,
			@PathVariable Integer num){
		System.out.println("detail진입");
		try {
			Map<String,Object> res = new HashMap<String, Object>();
			//sect가 before-modify일때는 공통부분이기 때문에 if문에 따로 적지 않았다.
			BoardDto board = boardService.boardDetail(num);
			res.put("board", board);
			if(sect.equals("only-detail")) {
				boardService.boardViewPlus(num);
				Boolean heart = boardService.isHeartBoard(null, num);
//				res.put("heart", heart);
				res.put("heart", false);
				
			} else if(sect.equals("after-modify")) {
				Boolean heart = boardService.isHeartBoard(null, num);
//			res.put("heart", heart);
				res.put("heart", false);
			}
			
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/img/{num}")
	public void imageView(@PathVariable Integer num , HttpServletResponse response) {
		try {
			boardService.readImage(num, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	@PostMapping("/boardwrite")
	public ResponseEntity<Integer> boardWrite(@ModelAttribute BoardDto board,
			List<MultipartFile> file){// ?
		System.out.println("fileName:"+file.get(0).getName());
		try {
			Integer num = boardService.boardWrite(board, file);
			return new ResponseEntity<Integer>(num,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/boardmodify")
	public ResponseEntity<Object> boardModify(@ModelAttribute BoardDto board,
		 @RequestParam(required = false,value = "file") List<MultipartFile> files){
		System.out.println("수정");
		try {
			Integer boardNum = boardService.boardModify(board, files);
			return new ResponseEntity<Object>(boardNum,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
 