package com.kosta.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.board.entiry.Board;
import com.kosta.board.entiry.Member;
import com.kosta.board.service.BoardService;
import com.kosta.board.util.PageInfo;

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
	
	@GetMapping({"/boardlist/{page}", "/boardlist"})
	public ResponseEntity<Map<String,Object>> boardList(@PathVariable(required = false) Integer page){
//		System.out.println("boardlist-controller 진입");
		try {
			PageInfo pageInfo = PageInfo.builder().curPage(page).build();
//			pageInfo.setCurPage(curpage==null? 1:curpage);
			List<Board> boards = boardService.boardListByPage(pageInfo);
			Map<String, Object> res = new HashMap<String, Object>();

			res.put("pageInfo", pageInfo);
			res.put("boardList", boards); 
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
	}   
	@GetMapping("/boarddetail/{sect}/{num}")
	public ResponseEntity<Map<String,Object>> boardDetail(@PathVariable String sect,
			@PathVariable Integer num){
		System.out.println("detail진입");
		try {
			Map<String,Object> res = new HashMap<String, Object>();
			//sect가 before-modify일때는 공통부분이기 때문에 if문에 따로 적지 않았다.
			Board board = boardService.boardInfo(num);
			res.put("board", board);
			if(sect.equals("only-detail")) {
				boardService.plusViesCount(num);
				Boolean heart = boardService.isSelectedBoardLike("pink", num);
				res.put("heart", heart);
				res.put("heart", false);
				
			} else if(sect.equals("after-modify")) {
				Boolean heart = boardService.isSelectedBoardLike("pink", num);
				res.put("heart", heart);
				res.put("heart", false);
			}
			return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	   @GetMapping("/writerinfo/{boardNum}")
	   public ResponseEntity<Member> writerInfo(@PathVariable Integer boardNum) {
	      try {
	         Member member = boardService.memberByBoardNum(boardNum);
	         return new ResponseEntity<Member>(member, HttpStatus.OK);
	      } catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	      }
	   }
	   
	   @GetMapping("/boardlistwi/{id}")
	   public ResponseEntity<List<Board>> boardListByWriterId(@PathVariable String id){
		   try {
			   List<Board> boardList = boardService.boardListByWriterId(id);
			   return new ResponseEntity<List<Board>>(boardList,HttpStatus.OK);
		   }catch (Exception e) {
			   e.printStackTrace();
			   return new ResponseEntity<List<Board>>(HttpStatus.BAD_REQUEST);
		   }
	   }
	   
	   @GetMapping("/boardlistwn/{name}")
	   public ResponseEntity<List<Board>> boardListByWriterName(@PathVariable String name){
		   try {
			   List<Board> boardList = boardService.boardListByWriterName(name);
			   return new ResponseEntity<List<Board>>(boardList,HttpStatus.OK);
		   }catch (Exception e) {
			   e.printStackTrace();
			   return new ResponseEntity<List<Board>>(HttpStatus.BAD_REQUEST);
		   }
	   }
	   //게시글번호의 좋아요를 눌렀는지?
	   @GetMapping("/boardlike/{num}")
		public ResponseEntity<Map<String,Object>> boardLike(@PathVariable Integer num){
			System.out.println("like 요청");
			try {
				Map<String,Object> res = new HashMap<>();
				Boolean selectBoard = boardService.selectBoardLike("pink", num); //Id는 토큰처리한거 가져올것
				res.put("isSelect", selectBoard);
				Integer likeCount = boardService.boardInfo(num).getLikecount();
				res.put("likeCount", likeCount);
				return new ResponseEntity<Map<String,Object>>(res,HttpStatus.OK);
			}catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
			}
		}
	   
	   
	
	
	
}
