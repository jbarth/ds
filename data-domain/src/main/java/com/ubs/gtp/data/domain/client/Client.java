package com.ubs.gtp.data.domain.client;

import java.math.BigDecimal;

public class Client {

	private String name;
    private String surname;
    private BigDecimal credit;
    private String username;
    private String password;
    private String pin;
    private Integer faAdvisor;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
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
	public Integer getFaAdvisor() {
		return faAdvisor;
	}
	public void setFaAdvisor(Integer faAdvisor) {
		this.faAdvisor = faAdvisor;
	}
    
    

}
