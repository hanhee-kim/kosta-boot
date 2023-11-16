package com.kosta.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dao.BoardDao;
import com.kosta.board.dto.Board;
import com.kosta.board.dto.FileVo;
import com.kosta.board.dto.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	// 게시판
	@Override
	public List<Board> boardListByPage(PageInfo pageInfo) throws Exception {
		int boardCount = boardDao.selectBoardCount();
		// 보드개수가 0일 경우
		if(boardCount==0) return null;
		
		// 전체 페이지, 시작 페이지, 끝 페이지
		int allPage = (int) Math.ceil((double) boardCount / 10);
		int startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
		int endPage = Math.min(startPage + 10 - 1, allPage);
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		if(pageInfo.getCurPage() > allPage) pageInfo.setCurPage(allPage);
		
		int row = (pageInfo.getCurPage() - 1) * 10 + 1;
		return boardDao.selectBoardList(row - 1);
	}
	
	// 게시글 검색
	@Override
	public List<Board> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception {
		int searchCount = boardDao.searchBoardCount(type, keyword);
		if(searchCount==0) return null;
		
		int allPage = (int) Math.ceil((double) searchCount / 10);
		int startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
		int endPage = Math.min(startPage + 10 - 1, allPage);
		
		pageInfo.setAllPage(allPage);
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		if(pageInfo.getCurPage() > allPage) pageInfo.setCurPage(allPage);
		
		int row = (pageInfo.getCurPage() - 1) * 10 + 1;
		
		return boardDao.searchBoardList(type, keyword, row);
	}
	
	// 게시글 상세
	@Override
	public Board boardDetail(Integer num) throws Exception {
		Board board = boardDao.selectBoard(num);
		if(board==null) throw new Exception("글 번호 오류");
		return board;
	}
	
	// 이미지 출력
	@Override
	public void readImage(Integer num, OutputStream out) throws Exception {
		// 경로 설정
		String dir = "c:/khh/upload/";
		FileInputStream fis = new FileInputStream(dir + num);
		FileCopyUtils.copy(fis, out);
		fis.close();
	}
	
	// 게시글 작성
	@Override
	public Integer boardWrite(Board board, List<MultipartFile> files) throws Exception {
		String dir = "c:/khh/upload/";
		
		if(files!=null && files.size() != 0) {
			String fileNums = "";
			for(MultipartFile file : files) {
				//file table에 insert
				FileVo fileVo = new FileVo();
				fileVo.setDirectory(dir);
				fileVo.setName(file.getOriginalFilename());
				fileVo.setSize(file.getSize());
				fileVo.setContenttype(file.getContentType());
				fileVo.setData(file.getBytes());
				boardDao.insertFile(fileVo);
				
				//upload폴더에 upload
				File uploadFile = new File(dir + fileVo.getNum());
				file.transferTo(uploadFile);
				
				//file번호 목록 만들기
				if(!fileNums.equals("")) {//파일비어있을땐 , 를 추가하지않는다
					fileNums += ",";
				}
				fileNums += fileVo.getNum();
			}
			System.out.println(fileNums);
			board.setFileurl(fileNums);
		}
		
		boardDao.insertBoard(board);
		return board.getNum();
	}
	
	// 게시글 수정
	@Override
	public Integer boardModify(Board board, List<MultipartFile> files) throws Exception {
		String dir = "c:/khh/upload/";
		if(files!=null && files.size()!=0) {
			String fileNums = "";
			for(MultipartFile file : files) {
				if(file.isEmpty()) {
					fileNums += (fileNums.equals("")?"":",")+file.getOriginalFilename();
				} else {
					// DB의 file 테이블에 컬럼 삽입
					FileVo fileVo = new FileVo();
					fileVo.setDirectory(dir);
					fileVo.setName(file.getOriginalFilename());
					fileVo.setSize(file.getSize());
					fileVo.setContenttype(file.getContentType());
					fileVo.setData(file.getBytes());
					boardDao.insertFile(fileVo);
					
					// 업로드 폴더에 파일 업로드
					File uploadFile = new File(dir + fileVo.getNum());
					file.transferTo(uploadFile);
					fileNums += (fileNums.equals("")?"":",")+fileVo.getNum();
				}
			}
			//파일url변경
			board.setFileurl(fileNums);
			/*
			// 기존에 파일이 있을 경우
			// file 테이블에서 해당 컬럼 삭제
			Board sboard = boardDao.selectBoard(board.getNum());
			String orgFileNum = sboard.getFileurl();
			if(orgFileNum !=null) {
				// 테이블에서 컬럼 삭제
				boardDao.deleteFile(Integer.valueOf(orgFileNum));
				
				// 업로드 폴더에서 삭제
				File orgFile = new File(dir + orgFileNum);
				if(orgFile.exists()) {
					orgFile.delete();
				}
			}
			*/
		}else {
			//파일이 없을때 board의 fileurl을 비워줌
			board.setFileurl(null);
		}
		// 기존 게시글 수정
		boardDao.updateBoard(board);
		return board.getNum();
	}
	
	// 게시글 삭제
	@Override
	public void boardDelete(Integer num) throws Exception {
		fileDelete(num);
		boardDao.deleteBoard(num);
	}
	
	//file 삭제
	private void fileDelete(Integer num) throws Exception{
		Board board = boardDao.selectBoard(num);
		if(board==null) throw new Exception("글 번호 오류");
		
		// 파일 삭제
		String fileUrl = board.getFileurl();
		if(fileUrl!=null && !fileUrl.equals("")) {
			String[] fileList = fileUrl.split(",");
			for(String fileNum : fileList) {
				// file 테이블에서 컬럼 삭제
				boardDao.deleteFile(Integer.valueOf(fileUrl));
				
				// upload 폴더에서 파일 삭제
				String dir = "c:/khh/upload/";
				File file = new File(dir + fileUrl);
				if(file.exists()) {
					file.delete();
				}
			}
		}
	}

	@Override
	public void boardViewPlus(Integer num) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
