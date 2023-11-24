package com.kosta.sec.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

//security 설정에서 loginProcessingUrl("/loginProc");
//loginProc 요청이 오면 자동으로 UserDatilsService 타입으로 Ioc(제어역전)되어있는 loadUserByUsername 함수가 실행된다.
//(AuthenticationManager를 거쳐서 AuthenticationProvider에 의해 호출됨)

@Service
public class PrincipalDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	//Security session(내부 Authentication(내부 UserDatilas))

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity); //세션에 들어가있음
		}
		return null;
	}

}
