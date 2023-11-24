package com.kosta.sec.config.oauth;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo{
	
	private Map<String, Object> attributes;
	
	public KakaoUserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	

	@Override
	public String getProviderId() {
		return String.valueOf(attributes.get("id"));
	}

	@Override
	public String getProvider() {
		return "kakao";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String)attributes.get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
//		attributes.get("properties").get
//		kakao_account;
//		return null;
		return (String)attributes.get("email");
	}

}
