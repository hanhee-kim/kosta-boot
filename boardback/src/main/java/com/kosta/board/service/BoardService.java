package com.kosta.board.service;

import java.io.OutputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.Board;
import com.kosta.board.dto.PageInfo;

public interface BoardService {
	List<Board> boardListByPage(PageInfo pageInfo) throws Exception;
	Board boardDetail(Integer num) throws Exception;
	
	void readImage(Integer num, OutputStream out) throws Exception;
	Integer boardWrite(Board board, List<MultipartFile> files) throws Exception;
	Integer boardModify(Board board, List<MultipartFile> files) throws Exception;
	void boardDelete(Integer num) throws Exception;
	
	List<Board> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception;
	
	//조회수
	void boardViewPlus(Integer num)throws Exception;
}
