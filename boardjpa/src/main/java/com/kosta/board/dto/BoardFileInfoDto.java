package com.kosta.board.dto;

import org.springframework.web.multipart.MultipartFile;

public class BoardFileInfoDto {
	private String type;
	private MultipartFile data;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MultipartFile getData() {
		return data;
	}
	public void setData(MultipartFile data) {
		this.data = data;
	}
	
	

}
