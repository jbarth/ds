package com.ubs.gtp.security.wrapper;

/**
 * author: Willy.Lai
 */

public class TokenWithId {

	private String token;
	private Integer id;

	public TokenWithId() {
		
	}
	
	public TokenWithId(String token, Integer id) {
		this.token = token;
		this.id = id;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
