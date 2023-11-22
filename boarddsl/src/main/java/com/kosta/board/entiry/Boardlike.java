package com.kosta.board.entiry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Boardlike {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	
	@Column
	private String member_id;
	@Column
	private Integer board_num;
	
	
}
