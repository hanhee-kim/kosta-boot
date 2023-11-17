package com.kosta.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {
	
	//primary key 인 애한테는 @Id
	@Id
	private String id;
	@Column
	private String name;
	@Column
	private Integer balance;
	@Column
	private String type;
	@Column
	private String grade;

	public void deposit(Integer money) {
		if(money>0) {
			balance += money;
		}
	}
	public void withdraw(Integer money) {
		if(balance>=money) {
			balance -= money;
		}
	}
	
}
