package com.kosta.board.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.board.dto.BoardDto;
import com.kosta.board.entity.Board;
import com.kosta.board.entity.Boardlike;
import com.kosta.board.entity.FileVo;
import com.kosta.board.entity.Member;
import com.kosta.board.repository.BoardLikeRepository;
import com.kosta.board.repository.BoardRepository;
import com.kosta.board.repository.FileVoRepository;
import com.kosta.board.util.PageInfo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private FileVoRepository fileVoRepository;
	@Autowired
	private BoardLikeRepository boardLikeRepository;

	@Override
	public List<BoardDto> boardListByPage(PageInfo pageInfo) throws Exception {
		// JPA는 페이지처리시 pageRequest를 사용한다
		// 매개변수는(몇페이지,페이지당몇개,정렬기준)
		// 첫번째 매개변수의 몇 페이지는 0부터 시작하기 때문에 현재페이지가 1페이지라면 여기선 0이되어야하기 때문에 -1 을 해주어야 한다
		PageRequest pageRequest = PageRequest.of(pageInfo.getCurPage() - 1, 10, Sort.by(Sort.Direction.DESC, "num"));
		Page<Board> pages = boardRepository.findAll(pageRequest);
		pageInfo.setAllPage(pages.getTotalPages());
		int startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
		int endPage = Math.min(startPage + 10 - 1, pageInfo.getAllPage());
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		// 쿼리한 데이터 가져오는 방법 pages.getContent()사용
		List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
		for (Board board : pages.getContent()) {
			boardDtoList.add(board.toDto());
		}
		return boardDtoList;
	}

	@Override
	public BoardDto boardDetail(Integer num) throws Exception {
		// 예외처리가 필요
		Optional<Board> oboard = boardRepository.findById(num);
		if (oboard.isEmpty())
			throw new Exception("글번호 오류");
		return oboard.get().toDto();
	}

	@Override
	public void readImage(Integer num, OutputStream out) throws Exception {
		// 경로 설정
		String dir = "c:/khh/upload/";
		FileInputStream fis = new FileInputStream(dir + num);
		FileCopyUtils.copy(fis, out);
		fis.close();
	}

	@Override
	public Integer boardWrite(BoardDto boardDto, List<MultipartFile> files) throws Exception {
String dir = "c:/khh/upload/";
		
		if(files!=null && files.size() != 0) {
			String fileNums = "";
			for(MultipartFile file : files) {
				//file table에 insert
				FileVo fileVo = FileVo.builder()
						.directory(dir)
						.name(file.getName())
						.size(file.getSize())
						.contenttype(file.getContentType())
						.data(file.getBytes()).build();
				fileVoRepository.save(fileVo);
//				new 하는 방법
//				FileVo filevo = new FileVo();
//				fileVo.setDirectory(dir);
//				fileVo.setName(file.getOriginalFilename());
//				fileVo.setSize(file.getSize());
//				fileVo.setContenttype(file.getContentType());
//				fileVo.setData(file.getBytes());
				
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
			boardDto.setFileurl(fileNums);
		}
		//board insert
		Board board = boardDto.toEntity();
		boardRepository.save(board);
		return board.getNum();
	}

	@Override
	public Integer boardModify(BoardDto boardDto, List<MultipartFile> files) throws Exception {
		Board board = boardRepository.findById(boardDto.getNum()).get();
		board.setSubject(boardDto.getSubject());
		board.setContent(boardDto.getContent());
		String dir = "c:/khh/upload/";
		if(files!=null && files.size()!=0) {
			String fileNums = "";
			for(MultipartFile file : files) {
				if(file.isEmpty()) {
					fileNums += (fileNums.equals("")?"":",")+file.getOriginalFilename();
				} else {
					// DB의 file 테이블에 컬럼 삽입
					FileVo fileVo = FileVo.builder()
							.directory(dir)
							.name(file.getName())
							.size(file.getSize())
							.contenttype(file.getContentType())
							.data(file.getBytes()).build();
					fileVoRepository.save(fileVo);
					
					// 업로드 폴더에 파일 업로드
					File uploadFile = new File(dir + fileVo.getNum());
					file.transferTo(uploadFile);
					fileNums += (fileNums.equals("")?"":",")+fileVo.getNum();
				}
			}
			//파일url변경
			board.setFileurl(fileNums);
			
		}else {
			//파일이 없을때 board의 fileurl을 비워줌
			board.setFileurl(null);
		}
		// 기존 게시글 수정
		boardRepository.save(board);
		return board.getNum();
	}

//	//file 삭제
//		private void fileDelete(Integer num) throws Exception{
//			Board board = boardRepository.findById(num).get();
//			if(board==null) throw new Exception("글 번호 오류");
//			
//			// 파일 삭제
//			String fileUrl = board.getFileurl();
//			if(fileUrl!=null && !fileUrl.equals("")) {
//				String[] fileList = fileUrl.split(",");
//				for(String fileNum : fileList) {
//					// file 테이블에서 컬럼 삭제
//					boardDao.deleteFile(Integer.valueOf(fileUrl));
//					
//					// upload 폴더에서 파일 삭제
//					String dir = "c:/khh/upload/";
//					File file = new File(dir + fileUrl);
//					if(file.exists()) {
//						file.delete();
//					}
//				}
//			}
//		}
	@Override
	public void boardDelete(Integer num) throws Exception {
//		fileDelete(num);
		boardRepository.deleteById(num);
	}

	@Override
	public List<BoardDto> searchListByPage(String type, String keyword, PageInfo pageInfo) throws Exception {
		PageRequest pageRequest = PageRequest
				.of(pageInfo.getCurPage() - 1, 10,
						Sort.by(Sort.Direction.DESC, "num"));
		Page<Board> pages = null;
		if(type.equals("subject")) {
			pages = boardRepository.findBySubjectContains(keyword,pageRequest);
		} else if(type.equals("content")) {
			pages = boardRepository.findByContentContains(keyword, pageRequest);
		} else if(type.equals("writer")) {
			pages = boardRepository.findByMember_Id(keyword, pageRequest);
		} else {
			return null;
		}
				
		pageInfo.setAllPage(pages.getTotalPages());
		int startPage = (pageInfo.getCurPage() - 1) / 10 * 10 + 1;
		int endPage = Math.min(startPage + 10 - 1, pageInfo.getAllPage());
		pageInfo.setStartPage(startPage);
		pageInfo.setEndPage(endPage);
		// 쿼리한 데이터 가져오는 방법 pages.getContent()사용
		List<BoardDto> boardDtoList = new ArrayList<BoardDto>();
		for (Board board : pages.getContent()) {
			boardDtoList.add(board.toDto());
		}
		return boardDtoList;
	}

	@Override
	public void boardViewPlus(Integer num) throws Exception {
		Board board = boardRepository.findById(num).get();
		System.out.println("viewCnt :"+board.getViewcount());
		board.setViewcount(board.getViewcount()+1);
		boardRepository.save(board);
	}

	@Override
	public Boolean isHeartBoard(String id, Integer num) throws Exception {
		Optional<Boardlike> boardLike = boardLikeRepository.findByMember_idAndBoard_num(id, num);
		if(boardLike.isPresent()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean selHeartBoard(String id, Integer num) throws Exception {
		Optional<Boardlike> boardLike = boardLikeRepository.findByMember_idAndBoard_num(id, num);
		Board board = boardRepository.findById(num).get();
		Boolean isSelect;
		if(boardLike.isPresent()) {	//이미 선택이 되어있으면
			boardLikeRepository.deleteById(boardLike.get().getNum());
			board.setLikecount(board.getLikecount()+1);
			isSelect = false;
		} else {	//선택되어있지 았았을경우
			Boardlike newBoardLike =  Boardlike.builder().member(Member.builder().id(id).build())
			.board(Board.builder().num(num).build()).build();
			boardLikeRepository.save(newBoardLike);
			board.setLikecount(board.getLikecount()-1);
			isSelect = true;
		}
		boardRepository.save(board);
		return isSelect;
	}

	@Override
	public void delHeartBoard(String id, Integer num) throws Exception {
		// TODO Auto-generated method stub

	}

}
