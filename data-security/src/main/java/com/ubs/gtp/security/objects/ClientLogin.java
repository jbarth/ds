package com.ubs.gtp.security.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ubs.gtp.data.domain.security.UsernamePasswordHashLogin;

/**
 * 
 * ClientLogin entity class, annotated with hibernate mappings.
 * This class is an implementation of {@link UsernamePasswordHashLogin}
 * 
 * @author Willy Lai
 * @since 0.1
 */

@Entity
@Table(name = "CLIENT_LOGIN")
public class ClientLogin implements UsernamePasswordHashLogin {

	private Integer clientId;
	private String username;
	private String passwordHash;
	private String pinHash;
	private int failedAttempts;
	
	public ClientLogin() {
		
	}
	
	@Column(name = "CLIENT_ID")
	public Integer getId() {
		return clientId;
	}
	public void setId(Integer clientId) {
		this.clientId = clientId;
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
	
	@Column(name = "PIN_HASH")
	public String getPinHash() {
		return pinHash;
	}
	public void setPinHash(String pinHash) {
		this.pinHash = pinHash;
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
		return "Client";
	}
	public void setType(String type) {
	
	}
	
	
}
