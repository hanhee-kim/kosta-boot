package com.kosta.sec.config.jwt;

public interface JwtProperties {//인터페이스의 모든 변수는 퍼블릭 스테틱 파이널
	String SECRET = "코스타";	//우리만 알고있는 서버 고유의 비밀키
	int EXPIRATION_TIME = 60000*60*24; //1000 : 1초 -> 24시간
	String TOKEN_PREFIX = "Bearer ";	//베리어 방식???
	String HEADER_STRING = "Authorization";
}
