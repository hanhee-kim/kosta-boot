package com.kosta.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;
import com.kosta.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;

	// 메인
	@GetMapping("/main")
	public String main() {
		return "main";
	}

	// 게시판
	@GetMapping({"/boardlist/{page}", "/boardlist"})
	public ModelAndView boardList(@PathVariable(required=false) Integer page) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(page==null? 1:page);

		ModelAndView mav = new ModelAndView();
		
		try {
			List<Board> boardList = boardService.boardListByPage(pageInfo);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo", pageInfo);
			mav.setViewName("boardlist");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	// 게시글 작성
	@GetMapping("/boardwrite")
	public String boardWrite() {
		return "writeform";
	}
	
	@PostMapping("/boardwrite")
	public ModelAndView boardWrite(@ModelAttribute Board board, MultipartFile file) {
		ModelAndView mav = new ModelAndView();
	
		try {
			boardService.boardWrite(board, file);
			// forward가 기본이므로, detailform으로 갈 때 board를 가져감
			mav.setViewName("detailform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	// 게시글 상세
	// 경로에 변수가 하나만 있을 경우, name 지정해주지 않아도 됨
	@GetMapping("/boarddetail/{num}/{page}")
	public ModelAndView boardDetail(@PathVariable(name="num") Integer num, @PathVariable(name="page") Integer page) {
		ModelAndView mav = new ModelAndView();
		
		try {
			Board board = boardService.boardDetail(num);
			mav.addObject("board", board);
			mav.addObject("page", page);
			mav.setViewName("detailform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	// 이미지 출력
	@GetMapping("/image/{num}")
	public void imageView(@PathVariable(name="num") Integer num, HttpServletResponse response) {
		try {
			boardService.readImage(num, response.getOutputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 게시글 수정
	@GetMapping("/boardmodify/{num}/{page}")
	public ModelAndView boardModify(@PathVariable("num") Integer num, @PathVariable@ModelAttribute("page") Integer page) {
		ModelAndView mav = new ModelAndView();
		
		try {
			Board board = boardService.boardDetail(num);
			mav.addObject("board", board);
			mav.setViewName("modifyform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	@PostMapping("/boardmodify")
	public ModelAndView boardModify(@ModelAttribute Board board, MultipartFile file, @RequestParam("page") Integer page) {
		ModelAndView mav = new ModelAndView();
		
		try {
			boardService.boardModify(board, file);
			// ModelAndView, Model과는 달리,
			// url에 붙여서 사용할 변수는 forward할 때
			// ${param.page}와 같은 방식으로 사용할 수 있음
			// 하지만 detailform.jsp에 ${page}로 사용했으므로
			// 아래와 같이 add
			mav.addObject("page", page);
			
			// 다른 방법으로는,
			// detailform.jsp에서 page 값이 null일 경우를 대비해줌
			// <c:set var="page" value="${page==null? 1:page}"/>
			// 다만, 이러한 경우 페이지가 무조건 1로 고정됨
			mav.setViewName("detailform");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		
		return mav;
	}
	
	// 게시글 삭제
	@GetMapping("/boarddelete/{num}/{page}")
	public String boardDelete(@PathVariable("num") Integer num, @PathVariable("page") Integer page, Model model) {
		try {
			boardService.boardDelete(num);
			return "redirect:/boardlist/" + page;
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("err", e.getMessage());
			return "error";
		}
	}
	
	// 게시글 검색
	@PostMapping("/boardsearch")
	public ModelAndView boardSearch(@RequestParam(value="page", required = false, defaultValue = "1") Integer page,
			@RequestParam("type") String type, @RequestParam("keyword") String keyword) {
		ModelAndView mav = new ModelAndView();
		try {
			// 검색옵션 미선택 혹은 검색어 미입력시 예외처리
			if (type.equals("all") || keyword == null || keyword.trim().equals("")) {
				mav.setViewName("redirect:/boardlist");
				return mav;
			}
	         
			PageInfo pageInfo = new PageInfo();
			pageInfo.setCurPage(page);
			List<Board> boardList = boardService.searchListByPage(type, keyword, pageInfo);
			mav.addObject("boardList", boardList);
			mav.addObject("pageInfo", pageInfo);
			mav.addObject("type", type);
			mav.addObject("keyword", keyword);
			mav.setViewName("boardlist");
		} catch(Exception e) {
			e.printStackTrace();
			mav.addObject("err", e.getMessage());
			mav.setViewName("error");
		}
		return mav;
	}
}