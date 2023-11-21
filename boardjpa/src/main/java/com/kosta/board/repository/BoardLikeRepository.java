package com.kosta.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.board.entity.Boardlike;

public interface BoardLikeRepository extends JpaRepository<Boardlike, Integer>{
	Optional<Boardlike> findByMember_idAndBoard_num(String id,Integer num);
}
