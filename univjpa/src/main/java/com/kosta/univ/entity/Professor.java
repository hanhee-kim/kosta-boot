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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	
	//Department join
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptno")
	private Department department;
	
	@Column
	private String email;
	@Column
	private String hpage;
	
	@OneToMany(mappedBy = "professor" ,fetch = FetchType.LAZY)
	private List<Student> studentList = new ArrayList<>();

}
