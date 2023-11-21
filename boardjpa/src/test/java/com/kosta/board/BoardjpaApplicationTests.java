package com.kosta.board;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.Boardlike;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.BoardLikeRepository;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.MemberRepository;
import com.kosta.board.service.BoardService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BoardjpaApplicationTests {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private BoardLikeRepository boardLikeRepository;
	
	@Autowired
	private BoardService boardService;

	@Test
	void contextLoads() {
	}

	
	@Test
	void selectBoard() {
		Board board = boardRepository.findById(1).get();
		 System.out.println(board);
	      System.out.println(board.getMember());
	   }
	   
	   @Test
	   void selectMember() {
	      Member member = memberRepository.findById("pink").get();
	      System.out.println(member);
	      for(Board board : member.getBoardList()) {
	         System.out.println(board);
	      }
	   }
	   
	   @Test
	   void selectMemberByEmail() {
		   Optional<Member> member = memberRepository.findByEmail("십길동");
		   System.out.println(member.get());
	   }
	   
	   @Test
	   void isHeart () {
		   Optional<Boardlike> boardLike = boardLikeRepository.findByMember_idAndBoard_num("pink",12);
		   if(boardLike.isPresent()) {
			   System.out.println(boardLike);
		   }else {
			   System.out.println("없음");
		   }
	   }
	   
	   @Test
	   void selHeartBoar() {
		   Boardlike boardlike = Boardlike.builder().member(Member.builder().id("pink").build())
                   .board(Board.builder().num(12).build()).build();

		   boardLikeRepository.save(boardlike);
	   }
	   
	   @Test
	   void delHeartBoard() {
		   Optional<Boardlike> boardLike = boardLikeRepository.findByMember_idAndBoard_num("pink", 12);
		   boardLikeRepository.deleteById(boardLike.get().getNum());
	   }
	   
	   @Test
	   void isHeartSelectedService() {
		   try {
			   System.out.println(
			   boardService.isHeartBoard("pink", 11));
		   }catch (Exception e) {
			   e.printStackTrace();
		   }
	   }
	   
	   

}
