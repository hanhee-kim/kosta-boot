package com.kosta.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.Member;

@Mapper
@Repository
public interface MemberDao {
	Member selectMember(String id) throws Exception;
	void insertMember(Member member) throws Exception;
}
