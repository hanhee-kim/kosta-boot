package com.kosta.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
@NoArgsConstructor
@Builder
public class Account {
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
			this.balance += money;
		}
	}
	public void withdraw(Integer money) throws Exception {
		if(balance<money) {
			throw new Exception("잔액부족");
		}
		this.balance -= money;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s,%s,%d,%s,%s", id,name,balance,type,(grade==null)?"null":grade);
	}
	
}
