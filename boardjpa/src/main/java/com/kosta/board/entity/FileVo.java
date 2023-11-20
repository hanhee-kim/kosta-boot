package com.kosta.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity(name="FILE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileVo {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;			// 순번
	@Column
	private String directory;		// 경로 (C:/khh/upload/)
	@Column
	private String name;			// 파일명 (ex: logo.png)
	@Column
	private Long size;				// 파일 byte 크기
	@Column
	private String contenttype;		// 파일 확장자 (ex: image/png)
	@Column
	private Date uploaddate;		// 업로드 날짜
	@Column
	private byte[] data;			// byte[] 배열로 저장된 파일 내용
	
	

}
