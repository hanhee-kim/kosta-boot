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

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;
import com.kosta.board.service.BoardService;

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
			List<Board> boards = boardService.boardListByPage(pageInfo);
			Map<String, Object> res = new HashMap<String, Object>();

			res.put("boardList", boards); // 안녕하세요?                         ㅏㅓㅏㅓㅏㅓㅏㅓㅏㅇ옹ㅇㄴㅇㄹㅇㅇ랑ㅇ
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
			List<Board> boardList = boardService.searchListByPage(type, keyword, pageInfo);
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
	
	@GetMapping("/boarddetail/{num}")
	public ResponseEntity<Board> boardDetail(@PathVariable Integer num){
		System.out.println("detail진입");
		try {
			Board board = boardService.boardDetail(num);
			boardService.bo
			System.out.println(board.getSubject());
			return new ResponseEntity<Board>(board,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Board>(HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<Integer> boardWrite(@ModelAttribute Board board,
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
	public ResponseEntity<Object> boardModify(@ModelAttribute Board board,
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
 