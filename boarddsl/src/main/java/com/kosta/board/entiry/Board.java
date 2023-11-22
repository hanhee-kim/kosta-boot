package com.kosta.board.entiry;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
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
	//디폴트값을 주기위해 다이나믹인설트(업데이트)를 사용
	@Column
	@ColumnDefault("0")
	private Integer viewcount;
	@Column
	@ColumnDefault("0")
	private Integer likecount;
	@Column
	@CreationTimestamp
	private Date writedate;
	@Column
	private String writer;
	
}
