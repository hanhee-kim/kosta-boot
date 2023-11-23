package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	private Integer studno;
	@Column
	private String id;
	@Column
	private String name;
	@Column
	private Integer grade;
	@Column
	private String jumin;
	@Column
	private Date birthday;
	@Column
	private String tel;
	@Column
	private Integer height;
	@Column
	private Integer weight;	
	@Column
	private Integer deptno1;
	@Column
	private Integer deptno2;
	@Column
	private Integer profno;
	
	

	
}
