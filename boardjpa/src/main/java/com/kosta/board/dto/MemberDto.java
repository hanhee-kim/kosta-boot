package com.kosta.board.dto;

import com.kosta.board.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
	private String id;
	private String name;
	private String password;
	private String email;
	private String address;
	

	//Dto => Entity 로 변환
		public Member toEntity() {
			return Member.builder()
					.id(id)
					.name(name)
					.password(password)
					.email(email)
					.address(address)
					.build();
		} //build로 안하고 new~해서 사용해도 괜찮다.
}
