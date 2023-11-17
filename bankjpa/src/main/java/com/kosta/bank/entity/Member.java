package com.kosta.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.kosta.bank.dto.MemberDto;

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
@ToString
@Builder
public class Member { //test 볼것
	
	@Id
	private String id;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String address;

	//Dto => Entity 로 변환
	public static Member toEntity(MemberDto memberDto) {
		return Member.builder()
				.id(memberDto.getId())
				.name(memberDto.getName())
				.password(memberDto.getPassword())
				.email(memberDto.getEmail())
				.address(memberDto.getAddress())
				.build();
	} //build로 안하고 new~해서 사용해도 괜찮다.
	
}
