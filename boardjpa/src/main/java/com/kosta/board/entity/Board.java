package com.kosta.board.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column
	private String subject;
	@Column
	private String content;
	@Column
	private String fileurl;
	
	
	//한 멤버가 여러 보드를 쓰기때문에 보드 입장에서 매니 투 원 관계
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="writer")
	private Member member;
	
	@Override
	public String toString() {
		return
				String.format("[%d,%s,%s,%s,%s]", num,subject,content,fileurl,member.getId());
	}
}
