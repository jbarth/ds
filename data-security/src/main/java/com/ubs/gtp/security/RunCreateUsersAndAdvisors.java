package com.ubs.gtp.security;

import java.util.Date;
import java.util.UUID;

import com.ubs.gtp.data.domain.security.Login;
import com.ubs.gtp.security.objects.AdvisorLogin;
import com.ubs.gtp.security.objects.ClientLogin;
import com.ubs.gtp.security.objects.Session;
import com.ubs.gtp.security.util.HibernateUtil;

/**
 * USED FOR DEBUG PURPOSES ONLY
 * Initializes the local database schema and populates it with dummy clients and advisors
 * 
 * @author Willy Lai
 * @since 0.1
 */

public class RunCreateUsersAndAdvisors {

	public static void main(String[] args) {
//		createClient(1, "alice", "alice1", "1234");
//		createClient(2, "bob", "bob1", "1234");
//		createClient(3, "charlie", "charlie1", "1234");
//		createAdvisor(1, "dan", "dan1");
//		createAdvisor(2, "eve", "eve1");
//		createAdvisor(3, "fred", "fred1");
//		createSession("Karim.Amer", "Advisor");
		
		SessionManager sessionManager = new SessionManager();
		Login login = sessionManager.getLogin("07244a27-9c04-47e1-9ff8-970778626c79");
		
		System.out.println(login.getType());
		
	}
	
	private static void createSession(String username, String usertype) {
		org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
		hibernateSession.beginTransaction();
		Session s = new Session();
		s.setLastAccessedTime(new Date());
		String token = UUID.randomUUID().toString();
		System.out.println(token);
		s.setSessionToken(token);
		s.setUsername(username);
		s.setUsertype(usertype);
		hibernateSession.save(s);
		hibernateSession.getTransaction().commit();
	}
	
	private static void createAdvisor(Integer advisortId, String username, String password) {
		org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
		hibernateSession.beginTransaction();
		AdvisorLogin al = new AdvisorLogin();
		al.setId(advisortId);
		al.setUsername(username);
		al.setPasswordHash(Hash.hash(password, al.getUsername()));
		al.setFailedAttempts(0);
		hibernateSession.save(al);
		hibernateSession.getTransaction().commit();
	}
	
	private static void createClient(Integer clientId, String username, String password, String pin) {
		org.hibernate.Session hibernateSession = HibernateUtil.getSessionFactory().getCurrentSession();
		hibernateSession.beginTransaction();
		ClientLogin cl = new ClientLogin();
		cl.setId(clientId);
		cl.setUsername(username);
		cl.setPasswordHash(Hash.hash(password, cl.getUsername()));
		cl.setPinHash(Hash.hash(pin, cl.getUsername()));
		cl.setFailedAttempts(0);
		hibernateSession.save(cl);
		hibernateSession.getTransaction().commit();
	}
}
