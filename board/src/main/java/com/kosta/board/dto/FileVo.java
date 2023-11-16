package com.kosta.board.dto;

import java.sql.Date;

public class FileVo {
	private Integer num;			// 순번
	private String directory;		// 경로 (C:/ris/upload/)
	private String name;			// 파일명 (ex: logo.png)
	private Long size;				// 파일 byte 크기
	private String contenttype;		// 파일 확장자 (ex: image/png)
	private Date uploaddate;		// 업로드 날짜
	private byte[] data;			// byte[] 배열로 저장된 파일 내용
	
//	public FileVo(Integer num, String directory, String name, Integer size,
//			String contenttype, Date uploadDate, byte[] data) {
//		this.num = num;
//		this.directory = directory;
//		this.name = name;
//		this.size = size;
//		this.contenttype = contenttype;
//		this.uploadDate = uploadDate;
//		this.data = data;
//	}
	
	public Integer getNum() {
		return num;
	}
	
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getDirectory() {
		return directory;
	}
	
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getSize() {
		return size;
	}
	
	public void setSize(Long size) {
		this.size = size;
	}
	
	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	public byte[] getData() {
		return data;
	}
	
	public void setData(byte[] data) {
		this.data = data;
	}
}
