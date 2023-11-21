package com.kosta.board.entity;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.kosta.board.dto.BoardDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
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
	@Column
	@CreationTimestamp
	private Date writedate;
	//디폴트값을 주기위해 다이나믹인설트(업데이트)를 사용
	@Column
	@ColumnDefault("0")
	private Integer viewcount;
	@Column
	@ColumnDefault("0")
	private Integer likecount;
//	@Column
//	private String writername;
	
	
	//한 멤버가 여러 보드를 쓰기때문에 보드 입장에서 매니 투 원 관계
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="writer")
	private Member member;
	// => 내 원래 컬럼명은   writer 인데 Member 와 조인한거야!
	
	@OneToMany(mappedBy = "board" , fetch = FetchType.EAGER)
	private List<Boardlike> boardLike = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return
				String.format("[%d,%s,%s,%s,%s]", num,subject,content,fileurl,member.getId());
	}
	
	public BoardDto toDto() {
		return BoardDto.builder()
				.num(num)
				.subject(subject)
				.content(content)
				.fileurl(fileurl)
				.writer(member.getId())
				.viewcount(viewcount)
				.likecount(likecount)
				.writedate(writedate)
				.build();
	}
}
