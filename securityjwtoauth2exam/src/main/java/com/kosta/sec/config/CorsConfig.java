package com.kosta.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.kosta.sec.config.jwt.JwtProperties;

@Configuration
public class CorsConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000"); //Access-Control-Allow-Origin (Response에 자동으로 추가해줌)
		config.addAllowedHeader("*"); //Access-Control-Allow-Headers
		config.addAllowedMethod("*"); //Access-Control-Allow-Method
		config.addExposedHeader(JwtProperties.HEADER_STRING); //클라이언트(리액트 등)가 응답에 접근할 수 있는 Header들을 추가한다.
		source.registerCorsConfiguration("/*", config);
		return new CorsFilter(source);
	}
}
