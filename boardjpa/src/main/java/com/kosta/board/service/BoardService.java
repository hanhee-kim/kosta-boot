package com.kosta.board.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.util.PageInfo;

public interface BoardService {
	List<BoardDto> boardListByPage(PageInfo pageInfo) throws Exception;
	BoardDto boardDetail(Integer num) throws Exception;
	
	void readImage(Integer num, OutputStream out) throws Exception;
	Integer boardWrite(BoardDto board, List<MultipartFile> files) throws Exception;
	Integer boardModify(BoardDto board, List<MultipartFile> files) throws Exception;
	void boardDelete(Integer num) throws Exception;
	
	List<BoardDto> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception;
	
	//조회수
	void boardViewPlus(Integer num)throws Exception;
	
	//좋아요
	Boolean isHeartBoard(String id, Integer num) throws Exception;
	Boolean selHeartBoard(String id,Integer num) throws Exception;
	void delHeartBoard(String id,Integer num) throws Exception;
}
