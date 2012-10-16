package com.ubs.gtp.security.prototype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubs.gtp.security.AuthenticationException;
import com.ubs.gtp.security.AuthenticationManager;
import com.ubs.gtp.security.SessionManager;
import com.ubs.gtp.security.objects.Session;
import com.ubs.gtp.security.wrapper.TokenWithId;

/**
 * Prototype, showing usage of the AuthenticationManager and SessionManager.
 * 
 * @author Willy.Lai
 * 
 */
public class Prototype {

	private static final transient Logger LOG = LoggerFactory.getLogger(Prototype.class);
	private static final SessionManager sessionManager = new SessionManager();
	private static final AuthenticationManager authenticationManager = new AuthenticationManager(sessionManager);

	public static void main(String args[]) throws InterruptedException, IOException {
		LOG.info("Starting Application");

		Thread.sleep(100);
		String username = promptUsername();
		String password = promptPassword();
		String pin = promptPin();
		
		try {
			TokenWithId sessionToken = authenticationManager.authenticateClient(username, password, pin);
			System.out.println(sessionToken);
			Session session = sessionManager.getSession(sessionToken.getToken());
			System.out.println(session);
			Thread.sleep(2000);
			authenticationManager.authenticateClient(username, password, pin);
			System.out.println(sessionManager.validateSession(sessionToken.getToken()));
			session = sessionManager.getSession(sessionToken.getToken());
			System.out.println(session);
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
		} 
		
	
	}

	private static String promptUsername() throws IOException {
		System.out.print("Username:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

	private static String promptPassword() throws IOException {
		System.out.print("Password:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

	private static String promptPin() throws IOException {
		System.out.print("Pin:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}
}
