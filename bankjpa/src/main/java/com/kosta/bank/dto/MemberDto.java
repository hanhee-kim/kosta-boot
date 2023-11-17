package com.kosta.bank.dto;

import com.kosta.bank.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {
	
	private String id;
	private String name;
	private String password;
	private String email;
	private String address;

	//Entity => Dto 로 변환
	public static MemberDto toDto(Member member) {
		return MemberDto.builder().id(member.getId()).name(member.getName())
				.password(member.getPassword()).email(member.getEmail())
				.address(member.getAddress()).build();
			
	}
}
