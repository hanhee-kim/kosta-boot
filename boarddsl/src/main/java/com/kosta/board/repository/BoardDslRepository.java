package com.kosta.board.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.kosta.board.entiry.Board;
import com.kosta.board.entiry.Boardlike;
import com.kosta.board.entiry.Member;
import com.kosta.board.entiry.QBoard;
import com.kosta.board.entiry.QBoardlike;
import com.kosta.board.entiry.QMember;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BoardDslRepository {
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	//member id 로 member 검색
	public Member findMemberByMemberId(String id) throws Exception {
		//일반 엔티티를 DSL로 싼 Q엔티티를 사용하는 것
		QMember member = QMember.member;
		return jpaQueryFactory.selectFrom(member)
			.where(member.id.eq(id)).fetchOne();
	}
	
	//member id 로 member name 검색
	public String findMemberNameByMemberId(String id) throws Exception {
		//일반 엔티티를 DSL로 싼 Q엔티티를 사용하는 것
		QMember member = QMember.member;
		return jpaQueryFactory.select(member.name)
				.from(member)
				.where(member.id.eq(id)).fetchOne();
	}
	//Tuple : 키와 벨류로 이루어진 DSL.CORE타입? 대충 파이썬 튜플느낌임
	public Tuple findMemberNameAndEmailByMemberId(String id)throws Exception{
		QMember member = QMember.member;
		return jpaQueryFactory.select(member.name,member.email)
				.from(member)
				.where(member.id.eq(id))
				.fetchOne();
	}
	
	public Member findMemberByIdAndPassword(String id,String password)throws Exception{
		QMember member = QMember.member;
		return jpaQueryFactory.selectFrom(member)
				.where(member.id.eq(id).and(member.password.eq(password)))
				.fetchOne();
	}
	
	//글번호로 작성자 정보 가져오기
	public Member findMemberByBoardNum(Integer num) {
		QMember member = QMember.member;
		QBoard board = QBoard.board;
		return jpaQueryFactory.selectFrom(member)
				.join(board)
				.on(member.id.eq(board.writer))
				.where(board.num.eq(num))
				.fetchOne();
//		쿼리문으로 작성한다면, 
//		select member.* 
//		from member join board 
//		on (member.id=board.writer) 
//		where board.num=10;
	}
	//글번호로 글 가져오기
	public Board findBoardByBoardNum(Integer num) throws Exception{
		QBoard board = QBoard.board;
		return jpaQueryFactory.selectFrom(board)
				.where(board.num.eq(num))
				.fetchOne();
	}
	//작성자 아이디로 글목록 가져오기
	public List<Board> findBoardListByWriterId(String id) throws Exception{
		QBoard board = QBoard.board;
		return jpaQueryFactory.select(board)
				.from(board)
				.where(board.writer.eq(id)).fetch();
			//하나가 아닌 리스트이기때문에 fetch();
	}
	
	//작성자 이름으로 글목록 가져오기
	public List<Board> findBoardListByWriterName(String name) throws Exception{
		QMember member = QMember.member;
		QBoard board = QBoard.board;
		return jpaQueryFactory.select(board)
				.from(board)
				.join(member)
				.on(board.writer.eq(member.id))
				.where(member.name.eq(name))
				.fetch();
	}
	
	//특정 페이지의 게시글 목록 가져오기
	public List<Board> findBoardListByPaging(PageRequest pageRequest) throws Exception{
		//옵셋과 리밋이 있다?
		QBoard board = QBoard.board;
		return jpaQueryFactory.selectFrom(board)
				.orderBy(board.num.desc())
				.offset(pageRequest.getOffset())
				.limit(pageRequest.getPageSize())
				.fetch();
	}
	
	//글 갯수 가져오기
	public Long findBoardCount() {
		QBoard board = QBoard.board;
		return jpaQueryFactory.select(board.count())
				.from(board)
				.fetchOne();
				
	}
	//Boardlike 에서 데이터 조회
	public Boardlike findBoardlike(String id,Integer num) throws Exception{
		QBoardlike boardlike = QBoardlike.boardlike;
		return jpaQueryFactory.select(boardlike)
				.from(boardlike)
				.where(boardlike.member_id.eq(id).and(boardlike.board_num.eq(num)))
				.fetchOne();		
	}
	//boardlike에서 데이터 갯수 조회
	public Long findIsBoardlike(String id, Integer num) throws Exception{
		QBoardlike boardlike = QBoardlike.boardlike;
		return jpaQueryFactory.select(boardlike.count())
				.from(boardlike)
				.where(boardlike.member_id.eq(id).and(boardlike.board_num.eq(num)))
				.fetchOne();
	}
	
//	public void updatePlusBoardViewCount(Integer num) throws Exception{
//		QBoard board = QBoard.board;
//		jpaQueryFactory.update(board)
//				.set(board.viewcount, null)
//	}
}
