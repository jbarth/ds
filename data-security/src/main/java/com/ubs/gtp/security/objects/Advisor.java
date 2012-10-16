package com.ubs.gtp.security.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADVISOR")
public class Advisor {
	
	public Advisor() {
		
	}
	
    private int advisorId;
    private String samAccountName;

    @Id
    @Column(name = "ADVISOR_ID")
	public int getAdvisorId() {
		return advisorId;
	}
	public void setAdvisorId(int advisorId) {
		this.advisorId = advisorId;
	}
	
	@Column(name = "SAMACCOUNTNAME")
	public String getSamAccountName() {
		return samAccountName;
	}
	public void setSamAccountName(String samAccountName) {
		this.samAccountName = samAccountName;
	}
    
    
}
