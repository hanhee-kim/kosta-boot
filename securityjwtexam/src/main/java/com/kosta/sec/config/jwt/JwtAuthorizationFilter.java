package com.kosta.sec.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.kosta.sec.config.auth.PrincipalDetails;
import com.kosta.sec.entity.User;
import com.kosta.sec.repository.UserRepository;

//인가 : 로그인 처리가 되야만 하는 요청이 들어왔을때 실행된다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	//베이직어덴티케이션필터는 인증정보를 걸러서 사용자로부터 가져온 토큰을 파싱해서 세션에 넣어 컨트롤러에서 활용할 수 있게끔한다.
	//토큰의 타당성만 체크
	//얘의 권한을 불러서 사용자의 토큰에 넣어줌

	private UserRepository userRepository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(JwtProperties.HEADER_STRING);
		System.out.println("header Authrization : "+header);
		
		if(header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		String token = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");//베리어를 빼줌(빼고 비교하기 위해서)
		System.out.println("token :"+token);
		String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token).getClaim("username").asString();
		
		if(username != null) {
			User user = userRepository.findByUsername(username);
			//검증은 프린시펄디테일즈이용해서 함
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			Authentication authentication = 
					new UsernamePasswordAuthenticationToken
					(principalDetails, null,principalDetails.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}

}
