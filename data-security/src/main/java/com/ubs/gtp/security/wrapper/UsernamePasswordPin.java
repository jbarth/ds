package com.ubs.gtp.security.wrapper;

/**
 * author: Willy Lai
 * 
 */

public class UsernamePasswordPin {

	private String username;
	private String password;
	private String pin;
	
	public UsernamePasswordPin() {
		
	}
	
	public UsernamePasswordPin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}
	
	
}
