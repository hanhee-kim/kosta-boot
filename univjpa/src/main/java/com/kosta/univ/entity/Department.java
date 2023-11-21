package com.kosta.univ.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data	//@Getter,@Setter,@ToString,@RequiredArgConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
	
	@Id
	private Integer deptno;
	@Column
	private String dname;
	@Column
	private Integer part;
	@Column
	private String build;
	
	@OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
	private List<Professor> professorList = new ArrayList<>();
	
	@OneToMany(mappedBy = "department1",fetch = FetchType.LAZY)
	private List<Student> student1 = new ArrayList<>();
	
	@OneToMany(mappedBy = "department2",fetch = FetchType.LAZY)
	private List<Student> student2 = new ArrayList<>();
}
