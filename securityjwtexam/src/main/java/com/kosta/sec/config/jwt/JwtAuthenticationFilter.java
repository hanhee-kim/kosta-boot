package com.kosta.sec.config.jwt;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.sec.config.auth.PrincipalDetails;
import com.kosta.sec.dto.LoginRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	//로그인시 사용하는 (활성화 되는)필터
	
	
	//스프링시큐리티에서 파라미터로 가지고 넘어옴
	private final AuthenticationManager authenticationManager;
	
	// 인증 요청시에 샐행되는 함수 => /login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter : 진입");
		//request에 있는 username과 password를 파싱해서 자바 Object로 받기
		ObjectMapper om = new ObjectMapper();
		LoginRequestDto loginRequestDto = null;
		try {
			loginRequestDto = om.readValue(request.getInputStream(), LoginRequestDto.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("JwtAuthenticationFilter : "+loginRequestDto);
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
		System.out.println("JwtAuthenticationFilter : 토큰생성완료");
		// authenticate함수가 호출되면 인증 프로바이더가 유저 디테일 서비스의
		// loadUserByUsername(토큰의 첫번째 파라미터) 를 호출하고
		// UserDetails를 리턴받아 토큰의 두번째 파라미터(credential)과
		// UserDetails(DB값)의 getPassword() 함수로 비교해서 동일하면
		// Authentication 객체를 만들어서 필터체인으로 리턴해준다.
		//패스워드가 일치하지 않으면 Authentication으로 받아올때 에러가 난다.
		//왜냐하면 눈에 보이지 않지만 두번째 파라미터로 이미 패스워드 비교까지 이루어지기 때문이다.
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
		System.out.println(principalDetails.getUser().getUsername());
		return authentication;
		
	}
	
	//정상적으로 완료되면 successfulAuthentication 호출
	@Override
	protected void successfulAuthentication
	(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		
		String jwtToken = JWT.create()
							.withSubject(principalDetails.getUsername())
							.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
							.withClaim("id", principalDetails.getUser().getId())
							.withClaim("username", principalDetails.getUser().getUsername())
							.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		//리액트에서 리덕스에 토큰을 저장하고 로그인을 해아만 가능한 순간마다 헤더에 토큰을 같이 보내주어야한다.
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX+jwtToken);
//		super.successfulAuthentication(request, response, chain, authResult);
	}
}
