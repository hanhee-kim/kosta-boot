package com.kosta.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

   // 요청을 먼저 받아서 Http에 보안과 관련된 설정을 해주는 메서드
   @Override
   protected void configure(HttpSecurity http) throws Exception {

      // CSRF 공격 차단
      http.csrf().disable();
      // 인가
      http.authorizeRequests()
         .antMatchers("/user/**").authenticated() // 로그인해야
         .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") // 로그인 & 권한
         .antMatchers("/manager/**").access("hasRole('ROLE_MANAGER')")
         .anyRequest().permitAll() // 나머지는 허용
         .and()
         .formLogin()
         .loginPage("/login")	//내가 만든 로그인 페이지를 사용할것이기 때문에 컨트롤러로 /login 요청을 보낸다.
         .loginProcessingUrl("/loginProc")	
         .defaultSuccessUrl("/");		//요청된 이전페이지로 가는 효과?예를 들면 글쓰기->로그인->글쓰기가도록
   }

}
