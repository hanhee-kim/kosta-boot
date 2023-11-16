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
	void boardWrite(Board board, MultipartFile file) throws Exception;
	void boardModify(Board board, MultipartFile file) throws Exception;
	void boardDelete(Integer num) throws Exception;
	
	List<Board> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception;
}
