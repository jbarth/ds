package com.ubs.gtp.security.objects;

/**
 * 
 * Username and hashed Password Login Data Wrapper Interface.
 * Subinterface of {@link com.ubs.gtp.security.objects.UsernamePasswordHashLogin}
 * 
 * @author Willy Lai
 * @since 0.1
 */

public interface UsernamePasswordHashLogin extends Login {

	public String getPasswordHash();

}
