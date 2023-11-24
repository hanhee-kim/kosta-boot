package com.kosta.sec.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//id는 자동으로 생성되어 따로 관리하는 코드??같은것
	private String username; 	//기본적으로 사용하는 id는 이 username을 가르킨다.
	private String password;
	private String email;
	private String roles;
	//소셜로그인인지 확인하기 위한 정보
	private String provider;
	private String providerId;
	
	@CreationTimestamp
	private Timestamp createDate;	//sql 꺼 

}
