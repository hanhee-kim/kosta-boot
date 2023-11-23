package com.kosta.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.kosta.board.entiry.Board;
import com.kosta.board.entiry.Boardlike;
import com.kosta.board.entiry.Member;
import com.kosta.board.repository.BoardDslRepository;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.BoardlikeRepository;
import com.kosta.board.repository.MemberRepository;
import com.kosta.board.util.PageInfo;
import com.querydsl.core.Tuple;
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDslRepository boardDslRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private BoardlikeRepository boardlikeRepository;
	
	@Override
	public Member memberInfo(String id) throws Exception {
		return boardDslRepository.findMemberByMemberId(id);
	}

	@Override
	public String memberName(String id) throws Exception {
		return boardDslRepository.findMemberNameByMemberId(id);
	}

	@Override
	public Boolean login(String id, String password) throws Exception {
		Member member = boardDslRepository.findMemberByIdAndPassword(id, password);
		if(member == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public Map<String, String> memberNameAndEmail(String id) throws Exception {
		Tuple tuple = boardDslRepository.findMemberNameAndEmailByMemberId(id);
		String name = tuple.get(0, String.class);
		String email = tuple.get(1, String.class);
		Map<String,String> res = new HashMap<String, String>();
		res.put("name", name);
		res.put("email", email);
		return res;
	}

	@Override
	public void join(Member member) throws Exception {
		memberRepository.save(member);
	}

	@Override
	public Member memberByBoardNum(Integer num) throws Exception {
		Member member = boardDslRepository.findMemberByBoardNum(num);
		if(member == null) throw new Exception("게시글 번호 오류");
		return member;
	}

	@Override
	public Board boardInfo(Integer num) throws Exception {
		Board board = boardDslRepository.findBoardByBoardNum(num);
		if(board == null)throw new Exception("게시글 번호 오류");
		return board;
	}

	@Override
	public List<Board> boardListByWriterId(String id) throws Exception {
		List<Board> boardList = boardDslRepository.findBoardListByWriterId(id);
		if(boardList.isEmpty())throw new Exception("작성자 아이디 오류");
		return boardList;
	}

	@Override
	public List<Board> boardListByWriterName(String name) throws Exception {
		List<Board> boardList = boardDslRepository.findBoardListByWriterName(name);
		if(boardList.isEmpty())throw new Exception("작성자 이름 오류");
		return boardList;
	}

	@Override
	public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
		PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage()-1, 10);
		List<Board> boardList = boardDslRepository.findBoardListByPaging(pageRequest);
		
		Long allCount = boardDslRepository.findBoardCount();
		Integer allPage = allCount.intValue()/pageRequest.getPageSize();
		if(allCount%pageRequest.getPageSize() != 0) {
			allPage += 1;
		}
		Integer startPage = (pageInfo.getCurPage()-1)/10*10+1;
		Integer endPage = Math.min(startPage+10-1, allPage);
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		
		return boardList;
	}

	@Override
	public Long boardCount() throws Exception {
		Long boardCnt = boardDslRepository.findBoardCount();
		return boardCnt;
	}

	@Override
	public Boolean selectBoardLike(String id, Integer num) throws Exception {
		Board board = boardRepository.findById(num).get();
		Boardlike boardLike = boardDslRepository.findBoardlike(id, num);
		if(boardLike == null) {
			boardlikeRepository.save(Boardlike.builder()
					.member_id(id)
					.board_num(num)
					.build());
			board.setLikecount(board.getLikecount()+1);	
			boardRepository.save(board);
			return true;
		}else {
			boardlikeRepository.deleteById(boardLike.getNum());
			board.setLikecount(board.getLikecount()-1);	
			boardRepository.save(board);
			return false;
		}
	}

	@Override
	public Boolean isSelectedBoardLike(String id, Integer num) throws Exception {
		Long boardLikeCnt = boardDslRepository.findIsBoardlike(id, num);
		if(boardLikeCnt == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void plusViesCount(Integer num) throws Exception {
		Board board = boardRepository.findById(num).get();
		board.setViewcount(board.getViewcount()+1);
		boardRepository.save(board);
	}

}
