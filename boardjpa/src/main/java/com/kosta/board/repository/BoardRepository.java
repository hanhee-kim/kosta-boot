package com.kosta.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	//
	//특정 단어를 찾는 Contains(카멜표기법으로 사용하며, 컬럼명과 같아야한다)
	Page<Board> findBySubjectContains(String subject,PageRequest pageRequest);
	Page<Board> findByContentContains(String content,PageRequest pageRequest);
	Page<Board> findByMember_Id(String writer,PageRequest pageRequest);
}
