package com.kosta.univ.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
//	@Column
//	private Integer deptno1;
//	
//	@Column
//	private Integer deptno2;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptno1")
	private Department department1;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "deptno2")
	private Department department2;
	
//	private Department department;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "profno")
	private Professor professor;

	@Override
	public String toString() {
		return String.format("%d,%s,%s,%d,%s,%s,%d,%d ,%d,%d", 
				studno,id,name,grade,jumin,tel,height,weight,department1.getDeptno(),
				department2==null? null:department2.getDeptno());
	}
	
}
