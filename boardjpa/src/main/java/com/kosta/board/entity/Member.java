package com.kosta.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.kosta.board.dto.MemberDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	
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
	
	//한사람이 여러 게시글리스트를 작성하기때문에 멤버입장에서는 일 대 다 관계
	@OneToMany(mappedBy = "member",fetch = FetchType.EAGER)
	private List<Board> boardList = new ArrayList<Board>();
	
	@Override
	public String toString() {
		return
				String.format("[%s,%s,%s,%s,%s]", id,name,password,email,address);
	}
	
	//Dto => Entity 로 변환
		public MemberDto toDto() {
			return MemberDto.builder()
					.id(id)
					.name(name)
					.password(password)
					.email(email)
					.address(address)
					.build();
		} //build로 안하고 new~해서 사용해도 괜찮다.
}
