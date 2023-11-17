package com.kosta.board.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardLikeDao {
	Integer selectBoardLike(@Param("id") String id, @Param("num") Integer num) throws Exception;

	void insertBoardLike(@Param("id") String id, @Param("num") Integer num) throws Exception;

	void deleteBoardLike(@Param("id") String id, @Param("num") Integer num) throws Exception;

}
