package com.kosta.sec.config.jwt;

public interface JwtProperties {
	String SECRET = "코스타"; //우리 서버 고유의 비밀키
	int EXPIRATION_TIME = 60000*60*24; //24시간
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}