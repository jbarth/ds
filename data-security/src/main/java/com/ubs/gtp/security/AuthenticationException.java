package com.ubs.gtp.security;

/**
 * This exception is thrown on any authentication failure.
 * 
 * @author Willy Lai
 * @since 0.1
 */
public class AuthenticationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthenticationException() {
		super();
	}
	
	public AuthenticationException(String message) {
		super(message);
	}

}
