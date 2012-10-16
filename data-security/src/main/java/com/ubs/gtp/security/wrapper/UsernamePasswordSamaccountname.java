package com.ubs.gtp.security.wrapper;

/**
 * author: Felix Pflanzl
 */
public class UsernamePasswordSamaccountname {

    private String username;
    private String password;
    private String samaccountname;

    public UsernamePasswordSamaccountname() {

    }

    public UsernamePasswordSamaccountname(String username, String password, String samaccountname) {
        this.username = username;
        this.password = password;
        this.samaccountname = samaccountname;
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
    
    public String getSamaccountname() {
    	return samaccountname;
    }
    
    public void setSamaccountname(String samaccountname) {
    	this.samaccountname = samaccountname;
    }

}

