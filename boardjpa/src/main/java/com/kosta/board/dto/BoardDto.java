package com.kosta.board.dto;


import java.sql.Date;

import com.kosta.board.entity.Board;
import com.kosta.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
	private Integer num;
	private String subject;
	private String content;
	private Date writedate;
	private String fileurl;
	private String writer;
	private Integer viewcount;
	private Integer likecount;
	private String writername;
	
	public Board toEntity() {
		//dto => Entity
		// 엔티티의 속성을 생성하는것
		// 내 변수를 사용하여 엔티티를 만들어주는것
		return Board.builder()
				.num(num)
				.subject(subject)
				.content(content)
				.fileurl(fileurl)
				.member(Member.builder().id(writer).build())
				.viewcount(viewcount)
				.likecount(num)
				.writedate(writedate)
				.build();
				
	}

}
