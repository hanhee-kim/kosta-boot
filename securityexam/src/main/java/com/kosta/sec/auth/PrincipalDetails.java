package com.kosta.sec.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kosta.sec.entity.User;
//스프링 시큐리티가 로그인정보를 사용할 수 있게끔 포장해줌
//시큐리티가 /loginProc 주소를 낚아채서 로그인을 진행시킨다.
//로그인 진행이 완료가 되면 시큐리티 세션을 만들어준다(Security ContextHolder)
//시큐리티 세션에 들어가는 타입은 Authentication 타입의 객체여야 한다.
//Authentication안에 User 정보를 넣어야 한다.
//그 User 오브젝트 타입은 UserDatils 타입이어야 한다.
public class PrincipalDetails implements UserDetails{
	//포함관계를 가지고 있는다
	private User user;
	//생성자를 통해 user초기화
	public PrincipalDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 권한.ROLE이 여러개일때?하나일떄? 모두 처리해주는 코드작성이 필요
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// 
				return user.getRoles();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// user정보를 보고 rock을 걸어줄때 시간을 계산해서..
		return true;
	}

}
