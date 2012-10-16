package com.ubs.gtp.security.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ubs.gtp.data.domain.security.UsernamePasswordHashLogin;

/**
 * 
 * AdvisorLogin entity class, annotated with hibernate mappings.
 * This class is an implementation of {@link UsernamePasswordHashLogin}
 * 
 * @author Willy Lai
 * @since 0.1
 */

@Entity
@Table(name = "ADVISOR_LOGIN")
public class AdvisorLogin implements UsernamePasswordHashLogin {

	private Integer advisorId;
	private String username;
	private String passwordHash;
	private int failedAttempts;
	
	public AdvisorLogin() {
		
	}
	
	@Column(name = "ADVISOR_ID")
	public Integer getId() {
		return advisorId;
	}
	public void setId(Integer advisorId) {
		this.advisorId = advisorId;
	}
	
	@Id
	@Column(name = "USERNAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "PASSWORD_HASH")
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	@Column(name = "FAILED_ATTEMPTS")
	public int getFailedAttempts() {
		return failedAttempts;
	}
	public void setFailedAttempts(int failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	@Transient
	public String getType() {
		return "Advisor";
	}
	public void setType(String type) {
		
	}
	
}
