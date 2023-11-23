package com.kosta.univ.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
public class Professor {
	
	@Id
	private Integer profno;
	@Column
	private String name;
	@Column
	private String id;
	@Column
	private String position;
	@Column 
	private Integer pay;
	@Column
	private Date hiredate;
	@Column
	private Integer bonus;
	@Column
	private Integer deptno;
	@Column
	private String email;
	@Column
	private String hpage;

}
